package com.fcgo.weixin.application.product;

import java.util.List;

import com.fcgo.weixin.persist.po.ProductImagePO;

/**
 * @author Ww
 */
public interface IProductImageService {
    /**
     * 根据商品ID 获取商品所有图片
     * 
     * @param productId
     * @return
     */
    public List<ProductImagePO> getByProductId(int productId);

    /**
     * 根据主键查询图片对象
     * 
     * @param id
     * @return
     */
    public ProductImagePO getById(int id);

    /**
     * 根据商品ID 获取商品第一张图片的URL
     * 
     * @param productId
     * @return
     */
    public String getImageUrlByProductId(int productId);
}
