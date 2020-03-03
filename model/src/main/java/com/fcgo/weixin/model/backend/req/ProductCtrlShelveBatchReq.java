package com.fcgo.weixin.model.backend.req;

import lombok.Data;

import java.util.List;
@Data
public class ProductCtrlShelveBatchReq {

    private List<Integer> productIds;

    private Integer status;
}
