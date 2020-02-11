package com.fcgo.weixin.interfaces;

import java.util.List;

/**
 * @author heshunfeng
 */
public interface Assembler<T, DT> {
    T toDTO(DT dt);

    DT fromDTO(T t);

    List<T> toDTOList(List<DT> dts);

    List<DT> fromDTOList(List<T> ts);

    T emptyDTO();

    DT emptyEntity();
}
