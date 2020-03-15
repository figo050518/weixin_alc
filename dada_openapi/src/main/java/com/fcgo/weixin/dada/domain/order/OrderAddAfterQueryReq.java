package com.fcgo.weixin.dada.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder@NoArgsConstructor@AllArgsConstructor
public class OrderAddAfterQueryReq {
    private String deliveryNo;
}
