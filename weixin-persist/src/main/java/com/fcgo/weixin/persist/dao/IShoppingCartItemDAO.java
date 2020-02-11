package com.fcgo.weixin.persist.dao;

import java.util.List;
import java.util.Map;

import com.fcgo.weixin.persist.generate.IShoppingCartItemMapper;
import com.fcgo.weixin.persist.po.ShoppingCartItemPO;

public interface IShoppingCartItemDAO extends IShoppingCartItemMapper {

    List<ShoppingCartItemPO> listShopItemByCartId(Map parm);

    int countShopItemByCartId(Map parm);
}
