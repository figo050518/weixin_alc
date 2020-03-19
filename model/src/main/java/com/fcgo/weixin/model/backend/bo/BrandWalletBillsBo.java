package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandWalletBillsBo {

    private Integer id;

    private Integer brandId;
    private String brandName;

    private Byte inOut;
    private String inOutDesc;

    private String orderCode;

    private BigDecimal amount;

    private Integer bizType;

    private String createTime;
}
