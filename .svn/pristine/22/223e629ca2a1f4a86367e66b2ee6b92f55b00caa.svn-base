package com.fcgo.weixin.interfaces;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshunfeng
 */
public abstract class AssemblerImpl<T, DT> implements Assembler<T, DT> {

    @Override
    public T toDTO(DT dt) {
        T t = emptyDTO();
        BeanUtils.copyProperties(dt, t);
        t = afterToDTO(t, dt);
        return t;
    }

    protected T afterToDTO(T t, DT dt) {
        return t;
    }

    @Override
    public List<T> toDTOList(List<DT> dts) {
        List<T> dtoList = new ArrayList<T>(dts.size());
        for (DT dt : dts) {
            dtoList.add(toDTO(dt));
        }
        return dtoList;
    }

    @Override
    public List<DT> fromDTOList(List<T> ts) {
        List<DT> entityList = new ArrayList<DT>(ts.size());
        for (T t : ts) {
            entityList.add(fromDTO(t));
        }
        return entityList;
    }

    protected DT afterFromDTO(T dto, DT dt) {
        return dt;
    }

    protected void beforeFromDTO(T dto){

    }

    @Override
    public DT fromDTO(T t) {
        beforeFromDTO(t);
        DT dt = emptyEntity();
        BeanUtils.copyProperties(t, dt);
        dt = afterFromDTO(t, dt);
        return dt;
    }
}
