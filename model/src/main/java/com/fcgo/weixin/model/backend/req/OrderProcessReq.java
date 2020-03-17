package com.fcgo.weixin.model.backend.req;

import lombok.Data;

@Data
public class OrderProcessReq {
    private String orderCode;
    /**
     *
     */
    private Integer status;
    /**
     * 1:自己配送 2:达达配送
     */
    private Integer deliverType;
}
