package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryTrace {
    private Integer id;

    private String orderCode;

    private String deliveryNum;

    private Integer status;

    private String cancelReason;

    private Integer cancelFrom;

    private Integer createTime;

    private Integer dadaUpdateTime;

    private Integer dmId;

    private String dmName;

    private String dmMobile;

}