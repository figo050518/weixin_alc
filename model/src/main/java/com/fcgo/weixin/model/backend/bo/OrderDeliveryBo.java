package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryBo {

    private String orderCode;

    private String deliveryNum;

    private BigDecimal fee;

    private BigDecimal deliverFee;

    private BigDecimal couponFee;

    private BigDecimal tips;

    private BigDecimal insuranceFee;

    private Integer status;

    private String statusDesc;

    private String createTime;

    private String updateTime;

    private List<OrderDeliveryTraceBo> orderDeliveryTraceList;
}
