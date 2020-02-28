package com.fcgo.weixin.persist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class OrderListQueryDto {

    private Integer brandId;

    private String orderCode;

    private Integer startTime;

    private Integer endTime;

    private String buyerPhone;

    private String status;

    private String payStatus;
}
