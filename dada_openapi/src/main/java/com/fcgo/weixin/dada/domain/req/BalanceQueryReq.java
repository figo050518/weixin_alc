package com.fcgo.weixin.dada.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceQueryReq {
    /**
     * 否
     * 查询运费账户类型（1：运费账户；2：红包账户，3：所有），默认查询运费账户余额
     */
    Integer category;
}
