package com.fcgo.weixin.controller.product;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.product.ShopCartService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.persist.po.ShoppingCartItemPO;
import com.fcgo.weixin.persist.po.ShoppingCartPO;

@RequestMapping("/uc/shopCart")
@Controller
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    /**
     * 购物车列表
     * 
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    Map findBylist(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Map result = new HashMap();
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 正常购物车商品信息
        List<ShoppingCartItemPO> shopcartitem = null;
        ShoppingCartPO shopcart = null;
        if (baseSessionUserDTO != null) {
            shopcart = shopCartService.findByUserId(baseSessionUserDTO.getUserId());
        }
        else {
            String cartCookie = CookieUtils.getCookieValue(request, "CART");
            if (StringUtils.isNotBlank(cartCookie)) {
                shopcart = shopCartService.findByCookieid(cartCookie);
            }
            // TODO
            shopcart = shopCartService.findByUserId(1);
        }
        if (shopcart != null) {
            shopcartitem = shopCartService.findShopItemByCartId(shopcart.getId());
        }
        result.put("shopcartitem", shopcartitem);
        return result;
    }

    /**
     * 初始化购物车列表
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String shopCartInit(Model model, HttpServletRequest request) {
        return "/product/shopCartList";
    }

    /**
     * 购物车添加
     * 
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String insertShopCart(HttpSession session, HttpServletResponse response, HttpServletRequest request,
            String productInfoJson) {
        ShoppingCartItemPO shoppingCartItemPO = new ShoppingCartItemPO();
        JSONArray jsonArray = JSONArray.fromObject(productInfoJson);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int sellerId = Integer.parseInt(jsonObject.get("sellerId").toString());
            int productId = Integer.parseInt(jsonObject.get("productId").toString());
            int specId = Integer.parseInt(jsonObject.get("specId").toString().trim());
            int productCount = Integer.parseInt(jsonObject.get("productCount").toString());
            shoppingCartItemPO.setSellerId(sellerId);
            shoppingCartItemPO.setSpecId(specId);
            shoppingCartItemPO.setProductId(productId);
            shoppingCartItemPO.setProductCount(productCount);
        }
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        shopCartService.insertShopCart(shoppingCartItemPO, baseSessionUserDTO, response, request);
        int shopCartCount = 0;
        ShoppingCartPO shopcart = null;
        if (baseSessionUserDTO != null) {
            shopcart = shopCartService.findByUserId(baseSessionUserDTO.getUserId());

        }
        else {
            String cartCookie = CookieUtils.getCookieValue(request, "CART");
            if (StringUtils.isNotBlank(cartCookie)) {
                shopcart = shopCartService.findByCookieid(cartCookie);
            }
        }
        if (shopcart != null) {
            shopCartCount = shopCartService.countShopItemByCartId(shopcart.getId());
        }
        return Integer.valueOf(shopCartCount).toString();
    }

    /**
     * 购物车删除
     * 
     * @return
     */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public @ResponseBody
    String deletebyid(HttpServletResponse response, HttpServletRequest request, @PathVariable String ids) {
        String[] idArray = ids.split(",");
        if (idArray != null && idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                shopCartService.deletebyIds((Integer.valueOf(idArray[i])));
            }
        }
        return "success";
    }
}
