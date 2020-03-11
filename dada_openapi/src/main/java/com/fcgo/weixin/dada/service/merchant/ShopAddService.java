package com.fcgo.weixin.dada.service.merchant;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseService;

/**
 * DATE: 18/9/4
 *
 * @author: wan
 */
public class ShopAddService extends BaseService {

    public ShopAddService(String params){
        super(UrlConstant.SHOP_ADD_URL, params);
    }
}
