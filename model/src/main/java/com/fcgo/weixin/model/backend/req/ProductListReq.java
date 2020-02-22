package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.Data;

@Data
public class ProductListReq extends PageRequestBO {

    private Integer uid;

    private String userName;

    private Integer brandId;

    private String productName;
}
