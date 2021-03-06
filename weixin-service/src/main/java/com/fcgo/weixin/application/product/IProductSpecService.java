package com.fcgo.weixin.application.product;

import java.util.List;

import com.fcgo.weixin.persist.po.ProductSpecPO;

/**
 * @author Ww
 */
public interface IProductSpecService {

    /**
     * 根据商品ID 查询所有商品属性
     * 
     * @param productId
     * @return
     */
    public List<ProductSpecPO> getByProductId(int productId);

    /**
     * 批量 根据商品ID 查询所有商品属性
     * 
     * @param productIds
     * @return
     */
    public List<ProductSpecPO> getByProductIds(List<Integer> productIds);

    /**
     * 属性主键查看属性
     * 
     * @param specId
     * @return
     */
    ProductSpecPO getBySpecId(int specId);
}
