package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateAuditStatus(Product record);

    int updateShelveStatus(Product record);

    int selectCntByBrandId(@Param("condition")Product product);

    List<Product> selectAllByBrandId(@Param("condition")Product condition,
                            @Param("offset")int offset,
                            @Param("limit")int limit);

    int selectCnt(@Param("condition")Product product);

    List<Product> selectAll(@Param("condition")Product condition,
                                     @Param("offset")int offset,
                                     @Param("limit")int limit);

}