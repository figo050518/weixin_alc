package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.Data;
import lombok.ToString;

@Data
public class WalletBillsListReq extends PageRequestBO {

    private Integer brandId;

    private String orderCode;

    private Integer inOut;
}
