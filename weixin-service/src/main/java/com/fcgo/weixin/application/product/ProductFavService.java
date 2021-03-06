package com.fcgo.weixin.application.product;

import java.util.List;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.UserProductFavPO;

public interface ProductFavService {
    /**
     * 收藏夹列表
     * 
     * @param userId
     * @return
     */
    public List<UserProductFavPO> listUserProductFav(int userId, Page page);

    /**
     * 收藏夹总数量
     * 
     * @param userId
     * @return
     */
    public int countUserProductFav(int userId);

    /**
     * 添加收藏夹
     * 
     * @param userProductFavPO
     * @return
     */

    public String addProductFav(UserProductFavPO userProductFavPO);

    /**
     * 根据主键删除收藏夹
     * 
     * @param id
     * @return
     */

    public boolean deleteProductFav(int id);

    /**
     * 判断当前用户时候已收藏该商品
     * 
     * @param userId
     * @param product
     * @return
     */
    public boolean getIsFav(int userId, int productId);
}
