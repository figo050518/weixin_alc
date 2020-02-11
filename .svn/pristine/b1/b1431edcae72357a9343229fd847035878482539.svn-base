package com.fcgo.weixin.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.interfaces.fcg.IFcgService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.FcgCateDTO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.SellerShopPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.fcg.FcgCate;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/uc/shop")
public class SellerIndexShopController {

    @Autowired
    private SellerShopService shopService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IFcgService fcgService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Title: index @Description: 根据参数跳转到店铺首页 @param @param shopId 考虑根据店铺ID 的情况 @param @param sellerId 用户ID @param @return
     *         参数 @return String 返回类型 @throws
     */
    @RequestMapping(value = "/index/{shopId}", method = RequestMethod.GET)
    public String index(@PathVariable String shopId, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        SellerShopPO shop = shopService.findById(String.valueOf(shopId));
        UserInfoPO userInfo = userInfoService.findById(shop.getSellerId());
        // 获取品类
        List<FcgCate> fcgCates = fcgService.findFcgCateList(userInfo.getFcgSellerId(), userInfo.getFcgToken());
        List<FcgCateDTO> dtoList = Lists.newArrayList();
        for (FcgCate fc : fcgCates) {
            FcgCateDTO d = new FcgCateDTO();
            d.setId(fc.getId());
            d.setText(fc.getName());
            dtoList.add(d);
        }
        FcgCateDTO d = new FcgCateDTO();
        d.setId(-1);
        d.setText("其他");
        dtoList.add(d);
        model.addAttribute("fcgCates", dtoList);
        // model.addAttribute("groupList", groupList);
        model.addAttribute("shop", shop);
        return "/shop/shopindex";
    }

    @SuppressWarnings({"unchecked", "rawtypes", "rawtypes"})
    @RequestMapping("/proList")
    public @ResponseBody
    Map proList(ProductPO product, String pageIndex, HttpServletRequest request, HttpServletResponse response) {
        Map result = new HashMap();
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        product.setShopId(baseSessionUserDTO.getShopId());
        List<ProductPO> products = productService.findProductList(product, page);
        if (product == null || products.isEmpty()) {
            result.put("products", 0);
        }
        else {
            result.put("products", products);
        }
        result.put("fcgCategoryId", product.getFcgCategoryId());
        return result;
    }
}
