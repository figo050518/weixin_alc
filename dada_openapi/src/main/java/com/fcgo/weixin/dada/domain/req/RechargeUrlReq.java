package com.fcgo.weixin.dada.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeUrlReq {
    Double amount;// 是	充值金额（单位元，可以精确到分）
    String category	;//	是	生成链接适应场景（category有二种类型值：PC、H5）
    String notify_url;// 否	支付成功后跳转的页面（支付宝在支付成功后可以跳转到某个指定的页面，微信支付不支持）
}
