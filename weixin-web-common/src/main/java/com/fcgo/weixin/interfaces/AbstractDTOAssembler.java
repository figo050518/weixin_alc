/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fcgo.weixin.common.log.LogCategory;

/**
 * DTOAssembler 抽象实现类
 * 
 * @author wangjinping
 */
public abstract class AbstractDTOAssembler<T, D> implements DTOAssembler<T, D> {

    protected Log logger = LogFactory.getLog(LogCategory.CONTROLLER.toString());

    /**
     * 实现领域对象列表-》 DTO列表的转换
     * 
     * @param pojoList
     * @return
     */
    public List<T> toDTOList(List<D> domainList) {
        if (domainList == null) {
            return null;
        }
        final List<T> dtoList = new ArrayList<T>(domainList.size());
        for (D domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }

    public List<D> fromDTOList(List<T> dtoList) {
        if (dtoList == null) {
            return null;
        }
        final List<D> domainList = new ArrayList<D>(dtoList.size());
        for (T dto : dtoList) {
            domainList.add(fromDTO(dto));
        }
        return domainList;
    }

    /**
     * @param strList 需要拼接的字符list
     * @param character 拼接字符
     * @return
     */
    protected String strMarger(List<String> strList, String character) {
        if (null == strList) {
            return null;
        }
        else {
            String margedStr = "";
            for (String str : strList) {
                margedStr += (str.trim() + character);
            }
            return StringUtils.stripEnd(margedStr, character);
        }
    }

    /**
     * @param str 需要拆分的字符
     * @param character 拆分字符标识
     * @return
     */
    protected String[] strSpliter(String str, String character) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        else {
            String[] splitedStr = str.split(character);
            return splitedStr;
        }
    }
}
