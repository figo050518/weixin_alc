package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseService;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class OrderAddService extends BaseService {

    public OrderAddService(String params){
        super(UrlConstant.ORDER_ADD_URL, params);
    }
}
