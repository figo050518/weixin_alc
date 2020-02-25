package com.fcgo.weixin.model.backend.req;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailReq {
    private String orderCode;
}
