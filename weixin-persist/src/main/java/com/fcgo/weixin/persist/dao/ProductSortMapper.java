package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.ProductSort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSort record);

    int insertSelective(ProductSort record);

    ProductSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSort record);

    int updateByPrimaryKey(ProductSort record);

    int selectCntByCondition(ProductSort condition);

    List<ProductSort> selectAllByCondtion(@Param("condition")ProductSort condition,
                                          @Param("offset")int offset,
                                          @Param("limit")int limit);
}