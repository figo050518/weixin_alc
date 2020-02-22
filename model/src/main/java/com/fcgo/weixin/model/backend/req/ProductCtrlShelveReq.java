package com.fcgo.weixin.model.backend.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCtrlShelveReq {
    private Integer uid;

    private String userName;

    private Integer brandId;

    private Integer productId;

    private Integer status;

}
