package com.fcgo.weixin.application.order;

import java.math.BigDecimal;

import com.fcgo.weixin.application.dto.OrderPayDTO;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.OrderInfoPO;

/**
 * 订单
 * 
 * @author Ww
 */
public interface IOrderInfoService {

    /**
     * 不同身份分页查询订单列表
     * 
     * @param baseSessionUserDTO
     * @param orderState
     * @param pageIndex
     * @return
     */
    public Page getOrderInfoList(BaseSessionUserDTO baseSessionUserDTO, String orderState, String buyerId, int pageIndex);

    /**
     * 根据订单ID 查看订单详情
     * 
     * @param orderId
     * @return
     */
    public OrderInfoPO getByOrderId(Integer orderId);

    /**
     * 付款
     * 
     * @param orderId
     * @param orderState
     * @param baseSessionUserDTO
     */
    public void updatePay(Integer orderId, String payType, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 发货
     * 
     * @param updateSendPro
     * @param baseSessionUserDTO
     */
    public void updateSendPro(Integer orderId, String logisticsNumber, String logisticsCompany,
            BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 确认收货
     * 
     * @param orderId
     * @param baseSessionUserDTO
     */
    public void updateSurePro(Integer orderId, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 取消订单
     * 
     * @param orderId
     * @param closeDesc
     * @param baseSessionUserDTO
     * @return
     */
    public void updateCancel(Integer orderId, String closeDesc, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 判断当前订单改商品是否申请售后
     * 
     * @param orderId
     * @param productId
     * @param productSpec
     * @return
     */
    public boolean getIsRefund(int orderId, int productId, String productSpec);

    /**
     * 运营人员查询订单列表
     * 
     * @param orderInfoPO
     * @param pageIndex
     * @return
     */
    public Page getOrderInfoListPage(OrderInfoPO orderInfoPO, int pageIndex);

    /**
     * 卖家修改订单总价
     * 
     * @param orderId
     * @param proAmount
     * @param baseSessionUserDTO
     */
    public void updateOrderPrice(int orderId, String proAmount, BaseSessionUserDTO baseSessionUserDTO);

    public BigDecimal getAmountByParentOrderId(Integer parentOrderId);

    public OrderPayDTO getOrderInfo4Pay(Integer parentOrderId);

    public boolean validateIsPay(String orderNumber);

    /**
     * 更新订单付款成功
     * 
     * @param orderNumber
     */
    public void updateOrderPayFinish(String orderNumber, String transactionId);

    /**
     * 如果有非常购商品，需要拆分订单
     * 
     * @param orderNumber
     */

    public void spiltOrder(String orderNumber);
}
