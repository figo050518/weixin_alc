package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandWalletBills {
    private Integer id;

    private Integer brandId;

    private Byte inOut;

    private String orderCode;

    private BigDecimal amount;

    private Integer bizType;

    private Integer createTime;

}