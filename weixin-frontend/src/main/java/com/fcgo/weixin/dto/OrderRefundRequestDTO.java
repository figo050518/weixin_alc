package com.fcgo.weixin.dto;

import java.util.List;

import com.fcgo.weixin.persist.po.OrderRefundImagePO;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;

/**
 * 售后订单组装数据
 * 
 * @author Ww
 */
public class OrderRefundRequestDTO {

    // 售后主表信息
    private OrderRefundRequestPO orderRefundRequestPO;
    // 售后图片表信息
    private List<OrderRefundImagePO> orderRefundImagePOs;
    // 1=自营，2=平台上架 商品来源
    private int productType;

    public OrderRefundRequestPO getOrderRefundRequestPO() {
        return orderRefundRequestPO;
    }

    public void setOrderRefundRequestPO(OrderRefundRequestPO orderRefundRequestPO) {
        this.orderRefundRequestPO = orderRefundRequestPO;
    }

    public List<OrderRefundImagePO> getOrderRefundImagePOs() {
        return orderRefundImagePOs;
    }

    public void setOrderRefundImagePOs(List<OrderRefundImagePO> orderRefundImagePOs) {
        this.orderRefundImagePOs = orderRefundImagePOs;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

}
