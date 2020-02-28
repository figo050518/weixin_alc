package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor@AllArgsConstructor
public class OrderListReq extends PageRequestBO {
    private String orderCode;

    private String startTime;

    private String endTime;

    private Integer brandId;

    private String buyerPhone;

    private String status;

    private String payStatus;

}
