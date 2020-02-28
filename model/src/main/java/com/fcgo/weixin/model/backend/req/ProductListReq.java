package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductListReq extends PageRequestBO {

    private Integer brandId;

    private Integer productSortId;

    private Integer status;

    private Integer verifyStatus;

    private String productName;
}
