package com.fcgo.weixin.dada.domain.resp;

import lombok.Data;

@Data
public class OrderCancelReason {
    Integer id;//理由编号
    String reason;//取消理由
}
