package com.fcgo.weixin.controller.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.product.IProductSpecService;
import com.fcgo.weixin.application.product.ProductFavService;
import com.fcgo.weixin.application.product.ShopCartService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.persist.po.ProductImagePO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.fcgo.weixin.persist.po.ShoppingCartPO;
import com.fcgo.weixin.persist.po.UserInfoPO;

/**
 * 商品
 * 
 * @author Ww
 */
@Controller
@RequestMapping("/uc/productInfo")
public class PdtProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IProductSpecService productSpecService;
    @Autowired
    private ProductFavService productFavService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 商品详细
     * 
     * @param productId
     * @return
     */
    @RequestMapping(value = "/pdtInfodetail")
    public String pdtProductdeail(String productId, Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // 先回去session中的用户信息，用户判断是否已收藏改商品，计算购物车数量
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 商品基本信息
        ProductPO productPO = productService.getById(Integer.parseInt(productId));
        // 商品图片信息
        List<ProductImagePO> productImagePOs = new ArrayList<ProductImagePO>();
        // 商品属性集合
        List<ProductSpecPO> productSpecPOs = new ArrayList<ProductSpecPO>();
        // 初始化商品所有属性库存
        int productCount = 0;
        // 来源，1=自营，2=平台上架 判断是否是自营商品
        if (productPO.getFromType() == 1) {
            // 自营商品调用
            productImagePOs = productImageService.getByProductId(Integer.parseInt(productId));
            productSpecPOs = productSpecService.getByProductId(Integer.valueOf(productId));
            for (ProductSpecPO productSpecPO : productSpecPOs) {
                productCount += productSpecPO.getStock();
            }
        }
        else {
            // 调用FCG接口
        }
        // 商品描述
        String productDesc = productPO.getProductDesc();
        model.addAttribute("productDesc", productDesc);
        model.addAttribute("productCount", productCount);
        model.addAttribute("productPO", productPO);
        model.addAttribute("productImagePOs", productImagePOs);
        model.addAttribute("productSpecPOs", productSpecPOs);
        // 卖家信息
        UserInfoPO sellerInfo = userInfoService.findById(productPO.getSellerId());
        model.addAttribute("sellerInfo", sellerInfo);
        // 登录之后判断是否收藏 1为收藏 0未收藏
        int isProductFav = 0;
        if (baseSessionUserDTO != null) {
            boolean isFavFlag = productFavService.getIsFav(baseSessionUserDTO.getUserId(), productPO.getId());
            // 如果返回true 已收藏
            if (isFavFlag) {
                // 返回1为已收藏状态 0为收藏
                isProductFav = 1;
            }
        }
        model.addAttribute("isProductFav", isProductFav);
        // 获取购物车数量
        int shopCount = 0;
        ShoppingCartPO shopcart = null;
        if (baseSessionUserDTO != null) {
            shopcart = shopCartService.findByUserId(1);
        }
        else {
            String cartCookie = CookieUtils.getCookieValue(request, "CART");
            if (StringUtils.isNotBlank(cartCookie)) {
                shopcart = shopCartService.findByCookieid(cartCookie);
            }
        }
        if (shopcart != null) {
            shopCount = shopCartService.countShopItemByCartId(shopcart.getId());
        }
        model.addAttribute("shopCount", shopCount);
        String userType = null;
        if (baseSessionUserDTO.getIsSeller()) {
            userType = "1";
        }
        model.addAttribute("userType", userType);
        model.addAttribute("sellerId", productPO.getSellerId());
        return "/product/goodsDetails";
    }

    /**
     * 查看属性
     * 
     * @param specId
     * @return
     */
    @RequestMapping(value = "getInfoByspecId")
    @ResponseBody
    public ProductSpecPO getById(String specId) {

        return productSpecService.getBySpecId(Integer.parseInt(specId));
    }
}
