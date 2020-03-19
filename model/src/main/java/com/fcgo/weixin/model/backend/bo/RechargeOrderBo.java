package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class RechargeOrderBo {


    private Integer id;

    private String orderCode;

    private Integer bizType;

    private Integer brandId;
    private String brandName;

    private BigDecimal amount;

    private Integer status;

    private String createTime;

    private String updateTime;

    private String payTime;
}
