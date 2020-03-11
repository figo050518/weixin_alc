package com.fcgo.weixin.dada.domain;

import com.fcgo.weixin.dada.utils.JSONUtil;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class BaseModel {

    public String toJson() {
        return JSONUtil.toJson(this);
    }
}
