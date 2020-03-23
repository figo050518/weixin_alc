package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryTraceBo {
    private Integer id;

    private String orderCode;

    /**
     * 达达运单号，默认为空
     */
    private String clientId;

    private String deliveryNum;

    private Integer status;

    private String statusDesc;

    private String cancelReason;

    private Integer cancelFrom;

    private String createTime;

    private String dadaUpdateTime;

    private Integer dmId;

    private String dmName;

    private String dmMobile;
}
