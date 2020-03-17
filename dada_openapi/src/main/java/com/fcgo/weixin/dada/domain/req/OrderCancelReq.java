package com.fcgo.weixin.dada.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder@AllArgsConstructor@NoArgsConstructor
public class OrderCancelReq {

    private String order_id;//	是	第三方订单编号
    private Integer cancel_reason_id;//是	取消原因ID（取消原因列表）
    private String cancel_reason;//否	取消原因(当取消原因ID为其他时，此字段必填)
}
