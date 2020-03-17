package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.convert.LogisticsOrderConvert;
import com.fcgo.weixin.convert.OrderDeliveryConvert;
import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.domain.order.OrderAddAfterQueryReq;
import com.fcgo.weixin.dada.domain.order.OrderCallBackReq;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.backend.constant.OrderConstant;
import com.fcgo.weixin.model.backend.req.OrderProcessReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.model.constant.OrderPayStatus;
import com.fcgo.weixin.model.constant.ShopDeliverType;
import com.fcgo.weixin.persist.dao.BrandWalletMapper;
import com.fcgo.weixin.persist.dao.OrderAddressMapper;
import com.fcgo.weixin.persist.dao.OrderDeliveryMapper;
import com.fcgo.weixin.persist.dao.OrderMapper;
import com.fcgo.weixin.persist.model.BrandWallet;
import com.fcgo.weixin.persist.model.Order;
import com.fcgo.weixin.persist.model.OrderAddress;
import com.fcgo.weixin.persist.model.OrderDelivery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author chenchao
 */
@Service
public class LogisticsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProxyService proxyService;

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private BrandWalletMapper brandWalletMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Autowired
    private LoginService loginService;

    @Value("${dada.order.callback.url}")
    private String ORDER_CALLBACK_URL;

    public DeliverFeeResp queryDeliverFee(Order order){
        String orderCode = order.getCode();
        OrderAddress orderAddress = orderAddressMapper.selectByOrderCode(orderCode);
        DeliverFeeReq dfr = LogisticsOrderConvert.buildDeliverFeeReq(order, buildOrderCallBackUrl(), orderAddress);
        return proxyService.queryDeliverFee(dfr);
    }

    /**
     * 创建达达配送订单
     * @param req
     * @throws SessionExpireException
     */
    public void addOrder(OrderProcessReq req) throws SessionExpireException {
        String orderCode = req.getOrderCode();
        if (StringUtils.isBlank(orderCode)){
            throw new ServiceException(401,"订单号错误");
        }
        LoginUserResp loginUserResp = loginService.getLoginUser();
        if (Objects.isNull(loginUserResp)){
            throw new SessionExpireException();
        }
        Integer brandId;
        Order orderCondition = Order.builder().code(orderCode).brandId(brandId=loginUserResp.getBrandId()).build();
        Order order = orderMapper.selectByOrderCode(orderCondition);
        if (Objects.isNull(order)){
            logger.warn("add order deliver fail not find order, req {} user {}", req, loginUserResp);
            throw new ServiceException(401, "订单非你所属");
        }
        String payStatus;
        if (Objects.isNull(payStatus=order.getPayStatus())){
            logger.warn("add order deliver fail order pay status illegal, req {} user {}", req, loginUserResp);
            throw new ServiceException(401, "订单支付状态异常");
        }
        if(!Integer.valueOf(payStatus).equals(OrderPayStatus.PAID.getCode())){
            logger.warn("add order deliver fail order pay status illegal,pay status {} req {} user {}", payStatus, req, loginUserResp);
            throw new ServiceException(401, "订单支付状态异常");
        }
        addOrderAfterCheck(order, ShopDeliverType.DADA);
    }

    public void addOrderAfterCheck(Order order, ShopDeliverType shopDeliverType){

        String orderCode = order.getCode();
        Integer brandId = order.getBrandId();
        //pre-fetch deliver fee
        DeliverFeeResp deliverFeeResp = queryDeliverFee(order);
        if (Objects.isNull(deliverFeeResp)){
            logger.warn("add dada order queryDeliverFee fail, {}", orderCode);
            throw new ServiceException(500, "运费计算异常,请稍后重试");
        }
        //check amount in wallet whether enough 2 pay
        BrandWallet brandWallet = brandWalletMapper.selectByBrandId(brandId);
        BigDecimal amountInWallet = brandWallet.getAmount();
        if (Objects.isNull(amountInWallet)){
            logger.warn("add dada order brandWallet null, {}", orderCode);
            throw new ServiceException(500, "初次使用,请先完成运费充值");
        }
        BigDecimal dadaDeliverFee = new BigDecimal(deliverFeeResp.getFee());
        if (dadaDeliverFee.compareTo(amountInWallet)<0){
            logger.warn("add dada order brandWallet not enough pay dadaDeliverFee, orderCode {},dadaDeliverFee{} amountInWallet {}",
                    orderCode,dadaDeliverFee,amountInWallet);
            throw new ServiceException(500, "运费余额不足，请充值");
        }
        //call submit dada-order
        String deliveryNo = deliverFeeResp.getDeliveryNo();
        OrderAddAfterQueryReq oaaqr = OrderAddAfterQueryReq.builder().deliveryNo(deliveryNo).build();
        DadaApiResponse addDadaOrderResp = proxyService.addOrderAfterQuery(oaaqr);
        if (!addDadaOrderResp.isOk()){
            throw new ServiceException(500, "达达接单系统异常,请稍后重试");
        }
        //
        OrderDelivery odCreateCondition = OrderDeliveryConvert.buildDOOfInsert(orderCode,deliveryNo, deliverFeeResp);
        orderDeliveryMapper.insertSelective(odCreateCondition);
        //update order shop deliver type
        Order uoc = new Order();
        uoc.setCode(orderCode);
        uoc.setDeliverType(shopDeliverType.getCode());
        int updateDeliverType = orderMapper.updateByOrderCode(uoc);
        logger.info("add dada order updateDeliverType [{},{}]", uoc, updateDeliverType);
    }



    public Object getCityCodeList(){
        return proxyService.getCityCodeList();
    }

    public String buildOrderCallBackUrl(){
        return ORDER_CALLBACK_URL + "/" + OrderConstant.CALL_BACK_API;
    }



    public void processCallBack(OrderCallBackReq req){

    }
}
