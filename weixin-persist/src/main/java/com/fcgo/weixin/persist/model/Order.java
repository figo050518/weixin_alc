package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private String code;

    private Integer brandId;

    private String brandPhone;

    private Integer buyerId;

    private String buyerPhone;

    private String status,
    /**
     * 期望值（当前），用于更新状态
     */
            exceptStatus;

    private String payStatus;

    private Double amount;

    private Short payType;

    private Integer payTime;

    private Integer createTime;

    private Integer updateTime;

    private Integer orderType;

    private Integer deliverType;

}