package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.BigDecimalHelper;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.convert.LogisticsOrderConvert;
import com.fcgo.weixin.convert.OrderDeliveryConvert;
import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.domain.order.OrderAddAfterQueryReq;
import com.fcgo.weixin.dada.domain.order.OrderCallBackReq;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.req.OrderCancelReq;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.domain.resp.OrderCancelReason;
import com.fcgo.weixin.dada.domain.resp.OrderCancelResp;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.backend.constant.OrderConstant;
import com.fcgo.weixin.model.backend.req.OrderProcessReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.model.constant.BillsInOutType;
import com.fcgo.weixin.model.constant.OrderDeliverType;
import com.fcgo.weixin.model.constant.OrderPayStatus;
import com.fcgo.weixin.model.constant.OrderStatus;
import com.fcgo.weixin.model.third.dada.DadaOrderStatus;
import com.fcgo.weixin.persist.dao.*;
import com.fcgo.weixin.persist.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenchao
 */
@Service
public class LogisticsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<OrderCancelReason> orderCancelReasons;

    @Autowired
    private ProxyService proxyService;

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderDeliveryTraceMapper orderDeliveryTraceMapper;

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

    @Autowired
    private OrderService orderService;

    @Autowired
    private WalletService walletService;

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
        addOrderAfterCheck(order, OrderDeliverType.DADA_DELIVER);
    }

    public void addOrderAfterCheck(Order order, OrderDeliverType shopDeliverType){
        String orderCode = order.getCode();
        Integer brandId = order.getBrandId();
        logger.info("addOrderAfterCheck orderCode {} shopDeliverType {}", orderCode, shopDeliverType);
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
        if (dadaDeliverFee.compareTo(amountInWallet)>0){
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
        int updateDeliverType = orderService.updateDeliverType(orderCode, shopDeliverType);
        logger.info("add dada order updateDeliverType [{},{},{}]", orderCode, shopDeliverType, updateDeliverType);
    }



    public Object getCityCodeList(){
        return proxyService.getCityCodeList();
    }

    public String buildOrderCallBackUrl(){
        return ORDER_CALLBACK_URL+ "/logistics/dada/" + OrderConstant.CALL_BACK_API;
    }

    private void checkCallBackSign(OrderCallBackReq req){
        //对client_id, order_id, update_time的值进行字符串升序排列，再连接字符串，取md5值
        Map<String,String> params = new HashMap<>(3);
        params.put("client_id", req.getClientId());
        params.put("order_id", req.getOrderId());
        params.put("update_time", Objects.nonNull(req.getUpdateTime()) ? String.valueOf(req.getUpdateTime()) : "");
        boolean checkResult = proxyService.checkSign(params, req.getSignature());
        logger.info("checkCallBackSign checkResult {}, req {}",checkResult, req);
        if (!checkResult){
            logger.warn("checkCallBackSign checkResult fail, req {}",checkResult, req);
            throw new ServiceException(401, "非法签名");
        }

    }

    public void processCallBack(OrderCallBackReq req){
        //check sign
        checkCallBackSign(req);
        //local data check
        String orderCode = req.getOrderId();
        String dadaOrderCode = req.getClientId();
        OrderDelivery condition = OrderDelivery.builder()
                .orderCode(orderCode)
                .deliveryNum(dadaOrderCode)
                .build();
        OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderCodeDeliverNum(condition);
        if (Objects.isNull(orderDelivery)){
            logger.warn("create dada order fail, now create one {}",req);
            //todo add
        }
        DadaOrderStatus orderStatus = DadaOrderStatus.getDadaOrderStatus(req.getOrderStatus());
        if (Objects.isNull(orderStatus)){
            logger.warn("process dada order CallBack fail,DadaOrderStatus is null,{}",req);
            return;
        }
        boolean delivryFail = false;
        switch (orderStatus){
            case CACELED:
            case EXPIRED:
            case CREATE_FAIL:
            case RECALL_GOODS_DELIVERED_FAIL:
            case RECALL_FINISH_DELIVERED_FAIL:
                delivryFail = true;
                break;
        }
        int cdt = DateUtil.getCurrentTimeSeconds();
        OrderDelivery oduc = OrderDelivery.builder().id(orderDelivery.getId())
                .status(orderStatus.getCode()).updateTime(cdt).build();
        int oduRows = orderDeliveryMapper.updateByPrimaryKeySelective(oduc);
        logger.info("processCallBack req {} rows {}", req, oduRows);
        OrderDeliveryTrace odtc = OrderDeliveryTrace.builder()
                .orderCode(orderCode).deliveryNum(dadaOrderCode)
                .status(req.getOrderStatus())
                .dadaUpdateTime(req.getUpdateTime())
                .cancelFrom(req.getCancelFrom())
                .cancelReason(req.getCancelReason())
                .dmId(req.getDmId())
                .dmName(req.getDmName())
                .dmMobile(req.getDmMobile())
                .createTime(DateUtil.getCurrentTimeSeconds())
                .build();

        int rows = orderDeliveryTraceMapper.insertSelective(odtc);
        logger.info("processCallBack finish, req {} rows {}", req, rows);
        if (delivryFail){
            cancelInnorOrder(orderDelivery);
        }
    }

    public List<OrderCancelReason> getAllOrderCancelReason(){
        if (CollectionUtils.isEmpty(this.orderCancelReasons)) {
            orderCancelReasons = proxyService.getOrderCancelReasonList();
        }
        return orderCancelReasons;
    }

    /**
     * 在订单待接单或待取货情况下，调用此接口可取消订单。
     * 注意：接单后1－15分钟内取消订单，运费退回。
     * 同时扣除2元作为给配送员的违约金
     * @param cancelReq
     * @throws SessionExpireException
     */
    public void cancelDeliverByBrand(OrderCancelReq cancelReq) throws SessionExpireException {
        String orderCode = cancelReq.getOrder_id();
        if (StringUtils.isBlank(orderCode)){
            throw new ServiceException(401, "没有订单号");
        }
        LoginUserResp loginUserResp = loginService.getLoginUser();
        if (Objects.isNull(loginUserResp)){
            throw new SessionExpireException();
        }
        Integer brandId;
        Order orderCondition = Order.builder().code(orderCode).brandId(brandId=loginUserResp.getBrandId()).build();
        Order order = orderMapper.selectByOrderCode(orderCondition);
        if (Objects.isNull(order)){
            logger.warn("cancelDeliverByBrand fail not find order, req {} user {}", cancelReq, loginUserResp);
            throw new ServiceException(401, "订单非你所属");
        }
        Integer cancel_reason_id;
        if (Objects.isNull(cancel_reason_id=cancelReq.getCancel_reason_id())){
            logger.warn("cancelDeliverByBrand fail at no cancel_reason_id, req {} user {}", cancelReq, loginUserResp);
            throw new ServiceException(401, "需要选择取消原因");
        }

        OrderCancelResp cancelRsp = proxyService.cancelOrder(cancelReq);
        Double deductFee;
        if (Objects.nonNull(deductFee=cancelRsp.getDeduct_fee()) && deductFee>0){

            OrderDelivery odc = OrderDelivery.builder()
                    .orderCode(orderCode)
                    .build();
            OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderCode(odc);
            //update status
            DadaOrderStatus orderStatus = DadaOrderStatus.CACELED;
            int cdt = DateUtil.getCurrentTimeSeconds();
            OrderDelivery oduc = OrderDelivery.builder().id(orderDelivery.getId())
                    .status(orderStatus.getCode()).updateTime(cdt).build();
            int oducRows = orderDeliveryMapper.updateByPrimaryKeySelective(oduc);
            logger.info("cancelDeliverByBrand req {} ,orderDelivery update {}",
                    cancelReq, oducRows);

            BigDecimal deliveryFee = orderDelivery.getCouponFee();
            BigDecimal deductFeeBD ;
            BigDecimal left = BigDecimalHelper.sub(deliveryFee, deductFeeBD=new BigDecimal(deductFee));
            logger.info("cancelDeliverByBrand req {} ,deductFee {} deliveryFee {} left {}",
                    cancelReq, deductFee, deliveryFee, left);
            walletService.plus(orderCode, brandId, deliveryFee, BillsInOutType.REFUND_OUT);
            //record deductFee
            walletService.substract(orderCode,brandId,deductFeeBD,BillsInOutType.PENALTY);
        }
        //reset deliver type of order
        //cancelInnorOrder(orderCode);
    }

    private void cancelInnorOrder(OrderDelivery orderDelivery){
        //OrderDeliverType shopDeliverType = OrderDeliverType.DELIVER;
        //int updateDeliverType = orderService.updateDeliverType(orderCode, shopDeliverType);
        String orderCode = orderDelivery.getOrderCode();
        Order oc = Order.builder().code(orderCode).build();
        Order po = orderMapper.selectByOrderCode(oc);
        OrderStatus orderStatus = OrderStatus.getOrderStatus(Integer.valueOf(po.getStatus()));
        if (OrderStatus.SELLER_PLAY_BUYER.equals(orderStatus)){
            return;
        }
        walletService.plus(orderCode,po.getBrandId(), orderDelivery.getFee(), BillsInOutType.REFUND_OUT);
    }
}
