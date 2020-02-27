package com.fcgo.weixin.model.backend.req;

import lombok.Data;

@Data
public class OrderProcessReq {
    private String orderCode;
    /**
     *
     */
    private Integer status;
}
