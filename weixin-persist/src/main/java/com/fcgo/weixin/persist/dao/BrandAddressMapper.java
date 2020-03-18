package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface BrandAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandAddress record);

    int insertSelective(BrandAddress record);

    List<BrandAddress> selectByBrandIds(@Param("brandIds") Collection<Integer> brandIds);

    BrandAddress selectByPrimaryKey(Integer id);

    BrandAddress selectByBrandId(Integer brandId);

    int updateByPrimaryKeySelective(BrandAddress record);

    int updateByPrimaryKey(BrandAddress record);
}