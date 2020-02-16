package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import lombok.Data;

@Data
public class AccountListReq extends PageRequestBO {

    private BrandBo brand;
}
