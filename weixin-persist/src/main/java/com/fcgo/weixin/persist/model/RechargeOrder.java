package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class RechargeOrder {
    private Integer id;

    private String orderCode;

    private Integer bizType;

    private Integer brandId;

    private BigDecimal amount;

    private Integer status;

    private Integer platformStatus;

    private Integer createTime;

    private Integer updateTime;

    private Integer payTime;


}