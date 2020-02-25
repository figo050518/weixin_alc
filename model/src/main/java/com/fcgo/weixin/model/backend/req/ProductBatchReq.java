package com.fcgo.weixin.model.backend.req;

import lombok.Data;

import java.util.List;

@Data
public class ProductBatchReq {
    private Integer verifyStatus;
    private List<Integer> ids;
}
