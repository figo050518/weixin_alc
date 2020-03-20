package com.fcgo.weixin.dada.service.merchant;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import lombok.ToString;

/**
 * DATE: 18/9/4
 *
 * @author: wan
 */
@ToString(callSuper = true)
public class ShopAddContext extends BaseServiceContext {

    public ShopAddContext(String params){
        super(UrlConstant.SHOP_ADD_URL, params);
    }
}
