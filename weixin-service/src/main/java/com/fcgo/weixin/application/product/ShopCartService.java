package com.fcgo.weixin.application.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.persist.po.ShoppingCartItemPO;
import com.fcgo.weixin.persist.po.ShoppingCartPO;

public interface ShopCartService {

    public ShoppingCartPO findByUserId(int userId);

    /**
     * 根据购物车主表ID查询
     * 
     * @param cartId
     * @param pageIndex
     * @return
     */

    public List<ShoppingCartItemPO> findShopItemByCartId(Integer cartId);

    public int countShopItemByCartId(Integer cartId);

    public ShoppingCartPO findByCookieid(String cartCookie);

    /**
     * 插入购物车主表
     * 
     * @param shoppingCartItemPO
     * @param baseSessionUserDTO
     * @param response
     * @param request
     */

    public void insertShopCart(ShoppingCartItemPO shoppingCartItemPO, BaseSessionUserDTO baseSessionUserDTO,
            HttpServletResponse response, HttpServletRequest request);

    /**
     * 插入购物车子表
     * 
     * @param shoppingCartItemPO
     * @param baseSessionUserDTO
     */

    public void insertShopCartItem(ShoppingCartItemPO shoppingCartItemPO, BaseSessionUserDTO baseSessionUserDTO);

    /**
     * 删除购物车
     * 
     * @param cartItemIds
     */

    public boolean deletebyIds(int cartItemId);

    public void mergeShopCart(BaseSessionUserDTO baseSessionUserDTO, HttpServletResponse response,
            HttpServletRequest request);

}
