package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.RechargeOrderConvert;
import com.fcgo.weixin.dada.domain.req.BalanceQueryReq;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.dada.domain.resp.BalanceResp;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.RechargeOrderBo;
import com.fcgo.weixin.model.backend.req.RechargeOrderListReq;
import com.fcgo.weixin.model.backend.resp.DadaRechargeUrlResp;
import com.fcgo.weixin.model.constant.PlatformRechargeStatus;
import com.fcgo.weixin.model.constant.RechargeOrderStatus;
import com.fcgo.weixin.persist.dao.RechargeOrderMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.RechargeOrder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DadaRechargeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProxyService dadaClient;

    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;

    @Autowired
    private BrandService brandService;


    public DadaRechargeUrlResp getDadaRechargeUrl(RechargeUrlReq req){
        String url = dadaClient.queryRechargeUrl(req);
        logger.info("getDadaRechargeUrl url {}",url);
        DadaRechargeUrlResp resp = DadaRechargeUrlResp.builder().url(url).build();
        return resp;
    }


    public BalanceResp getDadaLeftBalance(){
        BalanceQueryReq req = BalanceQueryReq.builder().category(3).build();
        return dadaClient.queryBalance(req);
    }

    public PageResponseBO<RechargeOrderBo> getList(RechargeOrderListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<RechargeOrderBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        RechargeOrder condition = check(req);
        int total = rechargeOrderMapper.selectCnt(condition);
        if (total==0){
            return pageBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<RechargeOrder> dolist = rechargeOrderMapper.selectAll(condition,offset, pageSize);
        Set<Integer> brandIds = new HashSet<>(dolist.size());
        dolist.stream().forEach(order -> {
            brandIds.add(order.getBrandId());
        });
        Map<Integer, Brand> brandMap = brandService.getIdBrandMap(brandIds);

        List<RechargeOrderBo> bos = dolist.stream().map(rechargeOrder -> RechargeOrderConvert.do2Bo(rechargeOrder,brandMap.get(rechargeOrder.getBrandId()))).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }
    private RechargeOrder check(RechargeOrderListReq req){
        Integer brandId = Objects.isNull(req.getBrandId()) ? null : req.getBrandId();
        String orderCode = StringUtils.isBlank(req.getOrderCode()) ? null : req.getOrderCode().trim();
        RechargeOrder condition = RechargeOrder.builder().brandId(brandId).orderCode(orderCode).build();
        return condition;
    }

    public void processDadaOrderOfBrand(RechargeOrderBo rechargeOrderBo){
        Integer platformStatusCode;
        if (Objects.isNull(platformStatusCode = rechargeOrderBo.getPlatformStatus())){
            logger.warn("process Dada Order Of Brand, req {}", rechargeOrderBo);
            return;
        }
        PlatformRechargeStatus platformRechargeStatus = PlatformRechargeStatus.getOrderStatus(platformStatusCode);

        if (Objects.isNull(platformRechargeStatus)){
            logger.warn("processDadaOrderOfBrand platformRechargeStatus null, {}", rechargeOrderBo);
            return;
        }
        switch (platformRechargeStatus){
            case TRYING: {
                prepayToDada(rechargeOrderBo);
                break;
            }
            case FINISH:{
                confirmPaid(rechargeOrderBo);
                break;
            }
        }
    }

    public void prepayToDada(RechargeOrderBo rechargeOrderBo){
        logger.info("prepayToDada {}", rechargeOrderBo);
        PlatformRechargeStatus tprs = PlatformRechargeStatus.TRYING;
        doUpdatePlatformStatus(rechargeOrderBo, tprs, "prepayToDada");
    }

    public void confirmPaid(RechargeOrderBo rechargeOrderBo){
        logger.info("confirmPaid {}", rechargeOrderBo);
        PlatformRechargeStatus tprs = PlatformRechargeStatus.FINISH;
        doUpdatePlatformStatus(rechargeOrderBo, tprs, "confirmPaid");
    }

    private int doUpdatePlatformStatus(RechargeOrderBo rechargeOrderBo,
                                       PlatformRechargeStatus tprs,
                                       String bizCase){
        Integer id;
        if (Objects.isNull(id=rechargeOrderBo.getId())||id<=0){
            throw new ServiceException(401,"ID参数错误");
        }
        RechargeOrder pro = rechargeOrderMapper.selectByPrimaryKey(rechargeOrderBo.getId());
        if (Objects.isNull(pro)){
            logger.warn("{} fail, id {}", bizCase, id);
            throw new ServiceException(401,"订单不存在");
        }
        RechargeOrderStatus exceptROS = RechargeOrderStatus.PAID;
        if (pro.getStatus() == null || exceptROS.getCode() != pro.getStatus().intValue()){
            logger.warn("{} fail, id {}", bizCase, id);
            throw new ServiceException(401,String.format("充值订单[%s]商户还没有支付",pro.getOrderCode()));
        }
        RechargeOrder rouc = new RechargeOrder();
        rouc.setId(id);
        rouc.setPlatformStatus(tprs.getCode());
        int rows = rechargeOrderMapper.updateByPrimaryKeySelective(rouc);
        logger.info("{} finish, req {} update result {}", bizCase, rechargeOrderBo, rows);
        return rows;
    }
}
