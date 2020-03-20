package com.fcgo.weixin.model.backend.bo;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBo {

    private Integer id;

    private String code;

    private Integer brandId;

    private String brandName;

    private String brandPhone;

    private Integer buyerId;

    private String buyerName;

    private String buyerPhone;

    private String status;

    private String payStatus;

    private Double amount;

    private Short payType;

    private String payTime;

    private String createTime;

    private String updateTime;

    private OrderAddressBo orderAddress;

    private List<OrderGoodsBo> orderGoodsList;

    private OrderDeliveryBo orderDelivery;
}
