package com.fcgo.weixin.application.impl.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.product.ShopCartService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.persist.generate.criteria.ShoppingCartCriteria;
import com.fcgo.weixin.persist.generate.criteria.ShoppingCartItemCriteria;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.fcgo.weixin.persist.po.ShoppingCartItemPO;
import com.fcgo.weixin.persist.po.ShoppingCartPO;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private IShoppingCartDAO shoppingCartDAO;

    @Autowired
    private IShoppingCartItemDAO shoppingCartItemDAO;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService imageService;

    @Autowired
    private IProductSpecDAO productSpecDAO;

    @Autowired
    private SellerShopService sellerShopService;

    @Override
    public ShoppingCartPO findByUserId(int userId) {
        ShoppingCartCriteria shoppingCartCriteria = new ShoppingCartCriteria();
        shoppingCartCriteria.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(0);
        List<ShoppingCartPO> shoppingCartPOs = shoppingCartDAO.selectByCriteria(shoppingCartCriteria);
        if (shoppingCartPOs != null && shoppingCartPOs.size() > 0) {
            return shoppingCartPOs.get(0);
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<ShoppingCartItemPO> findShopItemByCartId(Integer cartId) {
        Map parm = new HashMap();
        parm.put("shopCartId", cartId);
        return shoppingCartItemDAO.listShopItemByCartId(parm);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int countShopItemByCartId(Integer cartId) {
        Map parm = new HashMap();
        parm.put("shopCartId", cartId);
        return shoppingCartItemDAO.countShopItemByCartId(parm);
    }

    @Override
    public ShoppingCartPO findByCookieid(String cartCookie) {
        ShoppingCartCriteria shoppingCartCriteria = new ShoppingCartCriteria();
        shoppingCartCriteria.createCriteria().andCookieIdEqualTo(cartCookie).andIsDeleteEqualTo(0);
        List<ShoppingCartPO> shoppingCartPOs = shoppingCartDAO.selectByCriteria(shoppingCartCriteria);
        if (shoppingCartPOs != null && shoppingCartPOs.size() > 0) {
            return shoppingCartPOs.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void insertShopCart(ShoppingCartItemPO shoppingCartItemPO, BaseSessionUserDTO baseSessionUserDTO,
            HttpServletResponse response, HttpServletRequest request) {
        ShoppingCartPO shoppingCartPO = null;
        if (baseSessionUserDTO != null) {
            shoppingCartPO = validateIsExist(baseSessionUserDTO.getUserId(), null);
            if (shoppingCartPO == null) {
                shoppingCartPO = new ShoppingCartPO();
                shoppingCartPO.setCreateName(baseSessionUserDTO.getNickName());
                shoppingCartPO.setCreateTime(new Date());
                shoppingCartPO.setUpdateTime(new Date());
                shoppingCartPO.setUpdateName(baseSessionUserDTO.getNickName());
                shoppingCartPO.setUserId(baseSessionUserDTO.getUserId());
                shoppingCartPO.setIsDelete(0);
                shoppingCartDAO.insert(shoppingCartPO);
                shoppingCartItemPO.setShopCartId(shoppingCartPO.getId());
            }
            shoppingCartItemPO.setShopCartId(shoppingCartPO.getId());
        }
        else {
            String cookieValue = CookieUtils.getCookieValue(request, "CART");
            shoppingCartPO = validateIsExist(-1, cookieValue);
            if (shoppingCartPO == null) {
                if ((StringUtils.trimToEmpty(cookieValue).length() == 0)) {
                    cookieValue = request.getSession().getId();
                }
                shoppingCartPO = new ShoppingCartPO();
                shoppingCartPO.setCookieId(cookieValue);
                shoppingCartPO.setCreateName("guest");
                shoppingCartPO.setCreateTime(new Date());
                shoppingCartPO.setUpdateTime(new Date());
                shoppingCartPO.setUpdateName("guest");
                shoppingCartPO.setIsDelete(0);
                shoppingCartDAO.insert(shoppingCartPO);
                shoppingCartItemPO.setShopCartId(shoppingCartPO.getId());
            }
            shoppingCartItemPO.setShopCartId(shoppingCartPO.getId());
            String cookie = CookieUtils.getCookieValue(request, "CART");
            if ((StringUtils.trimToEmpty(cookie).length() == 0)) {
                CookieUtils.addCookie((HttpServletRequest) request, (HttpServletResponse) response, "CART", request
                        .getSession().getId(), 60 * 60 * 24 * 365 * 30, ".fcg.com");
            }

        }
        insertShopCartItem(shoppingCartItemPO, baseSessionUserDTO);

    }

    /**
     * 判断这个用户购物车中是否曾经加入过购物车
     * 
     * @param userId
     * @param cookie
     * @return
     */
    private ShoppingCartPO validateIsExist(int userId, String cookie) {
        ShoppingCartCriteria shoppingCartCriteria = new ShoppingCartCriteria();

        if (userId == -1 && StringUtils.isNotBlank(cookie)) {
            shoppingCartCriteria.createCriteria().andCookieIdEqualTo(cookie);
        }
        else {
            shoppingCartCriteria.createCriteria().andUserIdEqualTo(userId);
        }
        List<ShoppingCartPO> shopcart = shoppingCartDAO.selectByCriteria(shoppingCartCriteria);
        if (shopcart != null && shopcart.size() > 0) {
            return shopcart.get(0);
        }
        return null;

    }

    @Override
    public void insertShopCartItem(ShoppingCartItemPO shoppingCartItemPO, BaseSessionUserDTO baseSessionUserDTO) {
        ShoppingCartItemCriteria shoppingCartItemCriteria = new ShoppingCartItemCriteria();
        // 判断这个商品是自营的还是平台的
        ProductPO productPO = productService.getById(shoppingCartItemPO.getProductId());
        List<ShoppingCartItemPO> shoppingCartItemPOs = null;
        if (productPO != null && productPO.getFromType() == 1) {
            shoppingCartItemCriteria.createCriteria().andSpecIdEqualTo(shoppingCartItemPO.getSpecId())
                    .andIsDeleteEqualTo(0);
            shoppingCartItemPOs = shoppingCartItemDAO.selectByCriteria(shoppingCartItemCriteria);
        }
        if (productPO != null && productPO.getFromType() == 2) {
            shoppingCartItemCriteria.createCriteria().andFcgSpecIdEqualTo(shoppingCartItemPO.getSpecId())
                    .andIsDeleteEqualTo(0);
            shoppingCartItemPOs = shoppingCartItemDAO.selectByCriteria(shoppingCartItemCriteria);
        }
        if (shoppingCartItemPOs != null && shoppingCartItemPOs.size() > 0) {
            int productFinalProduct =
                    shoppingCartItemPO.getProductCount() + shoppingCartItemPOs.get(0).getProductCount();
            shoppingCartItemPOs.get(0).setProductCount(productFinalProduct);
            shoppingCartItemDAO.updateByPrimaryKey(shoppingCartItemPOs.get(0));
        }
        else {
            ShoppingCartItemPO shopcartItem = new ShoppingCartItemPO();
            shopcartItem.setShopCartId(shoppingCartItemPO.getShopCartId());
            shopcartItem.setProductUrl(imageService.getImageUrlByProductId(shoppingCartItemPO.getProductId()));
            shopcartItem.setProductCount(shoppingCartItemPO.getProductCount());
            shopcartItem.setProductId(shoppingCartItemPO.getProductId());
            if (baseSessionUserDTO != null) {
                shopcartItem.setCreateName(baseSessionUserDTO.getNickName());
                shopcartItem.setUpdateName(baseSessionUserDTO.getNickName());
            }
            else {
                shopcartItem.setCreateName("guest");
                shopcartItem.setUpdateName("guest");
            }
            shopcartItem.setUpdateTime(new Date());
            shopcartItem.setCreateTime(new Date());

            shopcartItem.setProductName(productPO.getProName());
            if (productPO != null && productPO.getFromType() == 1) {
                ProductSpecPO productSpecPO = productSpecDAO.selectByPrimaryKey(shoppingCartItemPO.getSpecId());
                shopcartItem.setProductUrl(imageService.getImageUrlByProductId(shoppingCartItemPO.getProductId()));
                shopcartItem.setSpecName(productSpecPO.getSpecName());
                shopcartItem.setSpecId(shoppingCartItemPO.getSpecId());
            }
            if (productPO != null && productPO.getFromType() == 2) {
                // TODO
                shopcartItem.setProductUrl(imageService.getImageUrlByProductId(shoppingCartItemPO.getProductId()));
                shopcartItem.setSpecName(null);
                shopcartItem.setFcgSpecId(shoppingCartItemPO.getSpecId());
            }

            shopcartItem.setIsDelete(0);
            // 根据商品ID找出属于哪个商家
            if (productPO != null) {
                shopcartItem.setShopId(productPO.getShopId());
                shopcartItem.setSellerId(productPO.getSellerId());
                shopcartItem.setShopName(sellerShopService.findById(Integer.valueOf(productPO.getShopId()).toString())
                        .getShopName());
            }
            shoppingCartItemDAO.insert(shopcartItem);

        }
    }

    @Override
    public boolean deletebyIds(int cartItemId) {
        ShoppingCartItemPO shoppingCartItemPO = new ShoppingCartItemPO();
        shoppingCartItemPO.setIsDelete(1);
        shoppingCartItemPO.setUpdateTime(new Date());
        ShoppingCartItemCriteria shoppingCartItemCriteria = new ShoppingCartItemCriteria();
        shoppingCartItemCriteria.createCriteria().andIdEqualTo(cartItemId);
        int i = shoppingCartItemDAO.updateByCriteriaSelective(shoppingCartItemPO, shoppingCartItemCriteria);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void mergeShopCart(BaseSessionUserDTO baseSessionUserDTO, HttpServletResponse response,
            HttpServletRequest request) {
        String cartCookie = CookieUtils.getCookieValue(request, "CART");
        if (StringUtils.isBlank(cartCookie)) {
            return;
        }
        ShoppingCartPO shopcartCookie = findByCookieid(cartCookie);
        if (shopcartCookie == null) {
            return;
        }
        ShoppingCartPO shopCartUser = findByUserId(baseSessionUserDTO.getUserId());
        if (shopCartUser == null) {
            // 把cookie里面所有的更新为改用户
            shopcartCookie.setCookieId(null);
            shopcartCookie.setUserId(baseSessionUserDTO.getUserId());
            shopcartCookie.setCreateName(baseSessionUserDTO.getNickName());
            shopcartCookie.setCreateTime(new Date());
            shopcartCookie.setUpdateName(baseSessionUserDTO.getNickName());
            shopcartCookie.setUpdateTime(new Date());
            shoppingCartDAO.updateByPrimaryKeySelective(shopcartCookie);
            return;
        }
        if (shopCartUser != null) {
            shopcartCookie.setCookieId(null);
            shopcartCookie.setUserId(baseSessionUserDTO.getUserId());
            shopcartCookie.setCreateName(baseSessionUserDTO.getNickName());
            shopcartCookie.setCreateTime(new Date());
            shopcartCookie.setUpdateName(baseSessionUserDTO.getNickName());
            shopcartCookie.setUpdateTime(new Date());
            shoppingCartDAO.updateByPrimaryKeySelective(shopcartCookie);
            List<ShoppingCartItemPO> shopCartItemsCookieList = findShopItemByCartId(shopcartCookie.getId());
            List<ShoppingCartItemPO> shopCartItemsUserList = findShopItemByCartId(shopCartUser.getId());
            for (ShoppingCartItemPO shopCartitemCookie : shopCartItemsCookieList) {
                for (ShoppingCartItemPO item : shopCartItemsUserList) {
                    if (item.getProductId() == shopCartitemCookie.getProductId()
                            && item.getSpecName().equals(shopCartitemCookie.getSpecName())) {
                        item.setIsDelete(1);
                        shoppingCartItemDAO.updateByPrimaryKeySelective(item);
                        shopCartitemCookie.setProductCount(shopCartitemCookie.getProductCount()
                                + item.getProductCount());
                    }
                }
                shopCartitemCookie.setShopCartId(shopCartUser.getId());
                shopCartitemCookie.setUpdateName(baseSessionUserDTO.getNickName());
                shopCartitemCookie.setUpdateTime(new Date());
                shoppingCartItemDAO.updateByPrimaryKeySelective(shopCartitemCookie);

            }
        }

    }

}
