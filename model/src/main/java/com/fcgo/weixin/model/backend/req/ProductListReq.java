package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductListReq extends PageRequestBO {

    private Integer uid;

    private String userName;

    private Integer brandId;

    private String productName;
}
