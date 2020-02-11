/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.interfaces;

import java.util.List;

/**
 * DTO<->DOAMIN 转换器类实现
 * 
 * @deprecated
 * @see com.focustech.crov.interfaces.Assembler
 * @author wangjinping
 */
public interface DTOAssembler<T, D> {

    /**
     * 领域对象-》DTO 对象的转换
     * 
     * @param domain 领域对象
     * @return
     */
    T toDTO(D domain);

    /**
     * DTO对象-》领域对象的转换
     * 
     * @param pojo POJO对象
     * @return
     */
    D fromDTO(T dto);

    /**
     * 领域对象列表-》DTO对象列表的转换
     * 
     * @param pojoList
     * @return
     */
    List<T> toDTOList(List<D> domains);

}
