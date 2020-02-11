/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.persist.mybatis.generator.plugin;

import java.util.List;
import java.util.Map.Entry;

import org.mybatis.generator.api.PluginAdapter;

/**
 * 定义我们自定义plugin的上下文 <br/>
 * 必须配置在所有的自定义plugin前面。
 * 
 * @author zhangxu
 */
public class CustomizeContextPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        for (Entry<Object, Object> entry : properties.entrySet()) {
            context.getProperties().setProperty(entry.getKey().toString(), entry.getValue().toString());
        }

        return true;
    }
}
