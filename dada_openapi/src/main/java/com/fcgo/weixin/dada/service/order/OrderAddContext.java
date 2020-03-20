package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import lombok.ToString;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
@ToString(callSuper = true)
public class OrderAddContext extends BaseServiceContext {

    public OrderAddContext(String params){
        super(UrlConstant.ORDER_ADD_URL, params);
    }
}
