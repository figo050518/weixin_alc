package com.fcgo.weixin.dada.service;

import com.fcgo.weixin.dada.config.UrlConstant;
import lombok.ToString;

/**
 * DATE: 18/9/4
 *
 * @author: wan
 */
@ToString(callSuper = true)
public class CityCodeContext extends BaseServiceContext {

    public CityCodeContext(String params){
        super(UrlConstant.CITY_CODE_URL, params);
    }
}
