package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    List<Brand> selectByIds(@Param("ids")Collection<Integer> ids);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}