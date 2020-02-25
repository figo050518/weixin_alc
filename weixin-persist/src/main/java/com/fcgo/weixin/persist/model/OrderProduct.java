package com.fcgo.weixin.persist.model;

import lombok.Data;

@Data
public class OrderProduct {
    private Integer id;

    private Integer orderId;

    private String orderCode;

    private Integer productTypeId;

    private String productType;

    private Integer productId;

    private String productName;

    private Double productPrice;

    private Integer productNumber;

    private Long createTime;

}