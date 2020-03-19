package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.BrandWalletBillsConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.BrandWalletBillsBo;
import com.fcgo.weixin.model.backend.req.WalletBillsListReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.persist.dao.BrandWalletBillsMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.BrandWalletBills;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class BrandWalletBillsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private BrandWalletBillsMapper brandWalletBillsMapper;

    @Autowired
    private BrandService brandService;


    public PageResponseBO<BrandWalletBillsBo> getList(WalletBillsListReq req) throws SessionExpireException {
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<BrandWalletBillsBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        LoginUserResp userResp = loginService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = AccountService.isAdmin(uid, userName);
        BrandWalletBills condition = check(req);
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        Supplier<Integer> totalSupplier;
        Supplier<List<BrandWalletBills>> prdListSupplier;
        if (isAdmin){
            logger.info("get WalletBills list user is admin, req {}", req);
            Integer brandId = req.getBrandId();
            condition.setBrandId(brandId);
            totalSupplier = ()-> brandWalletBillsMapper.selectCnt(condition);
            prdListSupplier = () -> brandWalletBillsMapper.selectAll(condition, offset, pageSize);
        }else{
            Integer brandId;
            if (Objects.isNull(brandId=userResp.getBrandId()) || brandId<1){
                throw new ServiceException(401, "品牌ID不正确");
            }
            logger.info("get WalletBills list user is brand, req {}", req);
            condition.setBrandId(brandId);
            totalSupplier = ()->  brandWalletBillsMapper.selectCnt(condition);
            prdListSupplier = () -> brandWalletBillsMapper.selectAll(condition, offset, pageSize);
        }

        int total = totalSupplier.get();
        if (total==0){
            return pageBuilder.build();
        }

        List<BrandWalletBills> dolist = prdListSupplier.get();
        Set<Integer> brandIds = new HashSet<>(dolist.size());
        dolist.stream().forEach(order -> {
            brandIds.add(order.getBrandId());
        });
        Map<Integer, Brand> brandMap = brandService.getIdBrandMap(brandIds);
        List<BrandWalletBillsBo> bos = dolist.stream().map(order->{
            Brand brand = brandMap.get(order.getBrandId());
            return BrandWalletBillsConvert.do2Bo(order, brand);
        }).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }


    private BrandWalletBills check(WalletBillsListReq req){
        BrandWalletBills condition = new BrandWalletBills();
        String orderCode;
        if (StringUtils.isNotBlank(orderCode = req.getOrderCode())){
            orderCode = orderCode.trim();
            condition.setOrderCode(orderCode);
        }
        return condition;
    }
}
