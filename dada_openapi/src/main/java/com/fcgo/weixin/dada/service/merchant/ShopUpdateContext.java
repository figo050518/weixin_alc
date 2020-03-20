package com.fcgo.weixin.dada.service.merchant;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import lombok.ToString;

@ToString(callSuper = true)
public class ShopUpdateContext extends BaseServiceContext {
    public ShopUpdateContext(String params) {
        super(UrlConstant.SHOP_UPDATE, params);
    }
}
