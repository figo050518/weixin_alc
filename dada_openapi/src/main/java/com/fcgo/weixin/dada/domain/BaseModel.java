package com.fcgo.weixin.dada.domain;

import com.fcgo.weixin.dada.utils.JSONUtil;
import lombok.Data;

/**
 *
 * @version 1.0
 * @author: chenchao
 * @date 2020.03.14
 */
public class BaseModel {
    public String toJson() {
        return JSONUtil.toJson(this);
    }
}
