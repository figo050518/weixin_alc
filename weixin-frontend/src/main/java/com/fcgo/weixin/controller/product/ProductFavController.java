package com.fcgo.weixin.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.product.ProductFavService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.controller.AbstractFrontController;
import com.fcgo.weixin.dto.OperatorInfoDTO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.UserProductFavPO;

@Controller
@RequestMapping("/uc/product/fav")
public class ProductFavController extends AbstractFrontController {

    @Autowired
    private ProductFavService productFavService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService imageService;

    /**
     * 收藏夹列表
     * 
     * @param model
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    Map favList(Model model, HttpServletRequest request, String pageIndex) {
        Map result = new HashMap();

        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");

        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        List<UserProductFavPO> userProductFavPOs =
                productFavService.listUserProductFav(baseSessionUserDTO.getUserId(), page);
        // TODO 根据产品ID查询价格
        result.put("userProductFavPOs", userProductFavPOs);
        result.put("totalCount", productFavService.countUserProductFav(baseSessionUserDTO.getUserId()));
        return result;
    }

    /**
     * 初始化收藏夹列表
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String favInit(Model model, HttpServletRequest request) {
        return "/product/productFav";
    }

    /**
     * 加入收藏夹
     * 
     * @param model
     * @param request
     * @param httpSession
     * @return
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String addFav(Model model, HttpServletRequest request, HttpSession httpSession, String productId) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserProductFavPO userProductFavPO = new UserProductFavPO();
        OperatorInfoDTO operatorInfoDTO = fillOperatorInfoDTO(baseSessionUserDTO);
        userProductFavPO.setCreateName(operatorInfoDTO.getAddName());
        userProductFavPO.setCreateTime(operatorInfoDTO.getCreateTime());
        userProductFavPO.setUpdateName(operatorInfoDTO.getAddName());
        userProductFavPO.setUpdateTime(operatorInfoDTO.getUpdateTime());
        userProductFavPO.setUserId(baseSessionUserDTO.getUserId());
        userProductFavPO.setFavState(1);
        userProductFavPO.setIsDelete(0);
        userProductFavPO.setProductId(Integer.valueOf(productId));
        ProductPO productPO = productService.getById(Integer.valueOf(productId));
        if (productPO != null) {
            userProductFavPO.setProductName(productPO.getProName());
            userProductFavPO.setProductUrl(imageService.getImageUrlByProductId(Integer.valueOf(productId)));
            userProductFavPO.setProductPrice(productPO.getMaxPrice().toString());
        }
        return productFavService.addProductFav(userProductFavPO);
    }

    /**
     * 删除收藏夹
     * 
     * @param model
     * @param request
     * @param httpSession
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.POST)
    public @ResponseBody
    String deleteFav(Model model, HttpServletRequest request, @PathVariable String ids) {
        String[] idArray = ids.split(",");
        if (idArray != null && idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                productFavService.deleteProductFav(Integer.valueOf(idArray[i]));
            }
        }
        return "success";
    }
}
