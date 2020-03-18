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
public class BrandWallet {
    private Integer id;

    private Integer brandId;

    private BigDecimal amount;

    private Integer bizType;

    private Integer status;

    private Integer createTime;

    private Integer updateTime;

}