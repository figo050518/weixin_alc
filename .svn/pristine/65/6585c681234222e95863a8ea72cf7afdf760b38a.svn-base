package com.fcgo.weixin.controller.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.product.IProductGroupService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.product.IProductSpecService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.ProductGroupPO;
import com.fcgo.weixin.persist.po.ProductImagePO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.uitl.BackendUtils;
import com.fcgo.weixin.uitl.PaginationContext;

/**
 * 商品
 * 
 * @author guangyang
 */
@Controller
@RequestMapping("/product")
public class PdtProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IProductSpecService productSpecService;
    @Autowired
    private IProductGroupService productGroupService ;

    /**
     * 商品列表
     * 
     * @param productId
     * @return
     */
    @RequestMapping("/pdtProductList")
    public String pdtProductList(Model model,
            HttpServletRequest request, HttpServletResponse response,ProductPO productPO) {
    	String pageIndex = request.getParameter("pageIndex");
    	Page page = new Page();
        if (StringUtils.isEmpty(pageIndex)) {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        
        List datas = productService.pdtProductList(productPO, page);
        //商品分组
        List<ProductGroupPO> productGroupPOs = productGroupService.pdtProductGroupAllList();
        
        model.addAttribute("products", datas);
        model.addAttribute("seachInfo",productPO);
        model.addAttribute("productGroups",productGroupPOs);
        model.addAttribute("paginationContext",new PaginationContext(page));
        
        return "/product/products";
    }
    /**
     * 商品详细
     * 
     * @param productId
     * @return
     */
    @RequestMapping("/pdtProductdeail")
    public String pdtProductdeail( Model model, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("id");
        if(StringUtils.isEmpty(productId)){
        	return "/product/product";
        }
        // 商品基本信息
        ProductPO productPO = productService.getById(Integer.parseInt(productId));
        model.addAttribute("productPO", productPO);
        // 商品图片信息
        List<ProductImagePO> productImagePOs = productImageService.getByProductId(Integer.parseInt(productId));
        model.addAttribute("productImagePOs", productImagePOs);
        // 商品属性集合
        List<ProductSpecPO> productSpecPOs = productSpecService.getByProductId(Integer.valueOf(productId));
        model.addAttribute("productSpecPOs", productSpecPOs);
        return "/product/product";
    }
    /**
     * 商品启/禁用
     * 
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/productActive")
    public @ResponseBody String productActive(HttpServletRequest request,ProductPO productPO) throws Exception {
    	JSONObject resultJson = new JSONObject();
    	try {
			resultJson.put("returnCode", "SUCCESS");
			resultJson.put("resultCode", "SUCCESS");
			if(productPO.getId()==null || productPO.getId()==0){
				resultJson.put("returnCode", "ERROR");
				resultJson.put("resultMsg", "参数id不能为空！");
				return resultJson.toString();
			}
			int type =productPO.getUpState();
			//查询
			productPO = productService.getById(productPO.getId());
			if(productPO == null){
				resultJson.put("returnCode", "ERROR");
				resultJson.put("resultMsg", "商品信息不存在！");
				return resultJson.toString();
			}
			//更新
			productPO.setUpState(type);
			productPO.setUpdateTime(new Date());
			productPO.setUpdateName(BackendUtils.getLoginUserName(request));
			productPO.setUpTime(new Date());
			boolean isSuccess = productService.productActive(productPO);
			resultJson.put("resultMsg", "操作成功!");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			resultJson.put("returnCode", "ERROR");
			resultJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}
        
        return resultJson.toString();
    }
}
