package com.fcgo.weixin.dada.domain.resp;

import lombok.Data;

@Data
public class DeliverFeeResp {
    Double distance;//		是	配送距离(单位：米)
    String deliveryNo;//		是	平台订单号
    Double fee;//		是	实际运费(单位：元)，运费减去优惠券费用
    Double deliverFee;//		是	运费(单位：元)
    Double couponFee;//		否	优惠券费用(单位：元)
    Double tips;//		否	小费(单位：元)
    /**
     * 否	保价费(单位：元)
      */
    Double insuranceFee;
}
