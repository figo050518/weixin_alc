package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class BrandWalletBo {
    private BigDecimal amount;

    private Integer brandId;
}
