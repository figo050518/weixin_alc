package com.fcgo.weixin.persist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fcgo.weixin.persist.generate.IProductSpecMapper;
import com.fcgo.weixin.persist.po.ProductSpecPO;

public interface IProductSpecDAO extends IProductSpecMapper {

    void batchInsert(List<ProductSpecPO> list);

    void deleteNotExist(@Param("list") List<Integer> list,@Param("productId")Integer productId);

    List<ProductSpecPO> findByProductIds(List<Integer> list);

    void batchUpdate(List<ProductSpecPO> list);

}