/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.persist.mybatis.generator.plugin;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * 扩展支持泛型
 * 
 * @author zhangxu
 */
public class FullyQualifiedJavaTypeExt extends FullyQualifiedJavaType {
    private FullyQualifiedJavaType parameterType;

    public FullyQualifiedJavaTypeExt(String fullTypeSpecification) {
        super(fullTypeSpecification);
    }

    public FullyQualifiedJavaTypeExt(String fullTypeSpecification, FullyQualifiedJavaType parameterType) {
        super(fullTypeSpecification);

        this.parameterType = parameterType;
    }

    /**
     * 重写此方法，增加泛型支持
     */
    @Override
    public String getShortName() {
        String shortName = super.getShortName();

        if (null != parameterType) {
            shortName = shortName + "<" + parameterType.getShortName() + "> ";
        }

        return shortName;
    }

    public FullyQualifiedJavaType getParameterType() {
        return parameterType;
    }

    public void setParameterType(FullyQualifiedJavaType parameterType) {
        this.parameterType = parameterType;
    }
}
