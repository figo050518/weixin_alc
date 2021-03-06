package com.fcgo.weixin.controller.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.interfaces.fcg.IFcgService;
import com.fcgo.weixin.application.product.IProductGroupService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.product.IProductSpecService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.constants.ProductSource;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.controller.product.convert.FcgProductConvert;
import com.fcgo.weixin.controller.product.convert.ProductConvert;
import com.fcgo.weixin.controller.product.convert.ProductGroupConvert;
import com.fcgo.weixin.controller.product.convert.ProductSpecConvert;
import com.fcgo.weixin.dto.FcgCateDTO;
import com.fcgo.weixin.dto.ParamDTO;
import com.fcgo.weixin.dto.ProductDTO;
import com.fcgo.weixin.dto.ProductSpecDTO;
import com.fcgo.weixin.persist.po.ProductGroupPO;
import com.fcgo.weixin.persist.po.ProductImagePO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.fcg.FcgCate;
import com.fcgo.weixin.persist.po.fcg.FcgProduct;
import com.google.common.collect.Lists;

import net.sf.json.JSONArray;

/**
 * @ClassName: PdtProductSellerController
 * @Description: 需要登录的product
 * @author zhonghui.geng
 * @date 2017年4月7日 下午4:39:20
 */
@Controller
@RequestMapping("/uc/product")
public class PdtProductSellerController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IProductSpecService productSpecService;
    @Autowired
    private IProductGroupService productGroupService;
    @Autowired
    private ProductConvert productConvert;
    @Autowired
    private ProductSpecConvert productSpecConvert;
    @Autowired
    private ProductGroupConvert groupConvert;
    @Autowired
    private IFcgService fcgService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FcgProductConvert fcgProductConvert;
    /**
     * @Title: preAddProduct
     * @Description:pre添加商品
     * @param @param source
     * @param @return 参数 商品来源
     * @return Map<String,Object> 返回类型
     * @throws
     */
    @RequestMapping(value = "/preAddProduct", method = RequestMethod.GET)
    public String preAddProduct(String source, Model model, HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        UserInfoPO userInfo =  userInfoService.findById(baseSessionUserDTO.getUserId());
        // 自营
        if (ProductSource.SELFSALE.getKey().equals(source)) {
            List<ProductGroupPO> groupList = productGroupService.findByShopId(baseSessionUserDTO.getShopId());
            //获取品类
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
            model.addAttribute("fcgCates", JSONArray.fromObject(dtoList));
            model.addAttribute("groupList", groupList);
            return "/product/sellerAddGoods";
        }
        // 平台
        if (ProductSource.PLATFORMSALE.getKey().equals(source)) {
            //获取品类
            List<FcgCate> fcgCates = fcgService.findFcgCateList(userInfo.getFcgSellerId(), userInfo.getFcgToken());
            model.addAttribute("fcgCates",  fcgCates);
            return "/product/theyPick";
        }
        return "";
 
    }

    /**
     * @Title: addSelfProduct
     * @Description:添加自营商品
     * @param @param source
     * @param @return 参数 商品数据
     * @return Map<String,Object> 返回类型
     * @throws
     */
    @RequestMapping("/addSelfProduct")
    public @ResponseBody
    String addSelfProduct(@RequestBody ProductDTO product, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        ProductPO pro = productConvert.convertToDomain(product);
        pro.setCreateName(baseSessionUserDTO.getNickName());
        pro.setUpdateName(baseSessionUserDTO.getNickName());
        pro.setSellerId(baseSessionUserDTO.getUserId());
        pro.setShopId(baseSessionUserDTO.getShopId());
        List<ProductSpecDTO> specList = product.getSepcList();
        List<ProductSpecPO> spList = new ArrayList<ProductSpecPO>();
        for (ProductSpecDTO productSpecDTO : specList) {
            ProductSpecPO p = new ProductSpecPO();
            p.setSpecName(productSpecDTO.getSpecName());
            p.setStock(productSpecDTO.getStock());
            p.setSalesPrice(productSpecDTO.getSalesPrice());
            spList.add(p);
        }
        int i = productService.addProduct(pro, spList,product.getImgUrlList());
        if (i > 0) {
            return "success";
        }
        return "";

    }
    
   /**
    * 添加自营商品
    * @param product
    * @return
    */
    @RequestMapping("/addPlatProduct")
    public @ResponseBody
    String addPlatProduct(@RequestBody FcgProduct product,Model model, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        ProductDTO productDto=fcgProductConvert.convertToDTO(product);
        ProductPO pro = productConvert.convertToDomain(productDto);
        pro.setMaxPrice(this.mutiIncrease(pro.getMaxPrice(),product.getIncrease()));
        pro.setMinPrice(this.mutiIncrease(pro.getMinPrice(),product.getIncrease()));
        pro.setCreateName(baseSessionUserDTO.getNickName());
        pro.setUpdateName(baseSessionUserDTO.getNickName());
        pro.setSellerId(baseSessionUserDTO.getUserId());
        pro.setShopId(baseSessionUserDTO.getShopId());
        pro.setAmountIncrease(product.getIncrease());
        int i = productService.addPlatProduct(pro, null,productDto.getImgUrlList());
        if (i > 0) {
            return "success";
        }
        return "";

    }

    private BigDecimal mutiIncrease(BigDecimal maxPrice, String increase) {
    	if(increase.isEmpty()){
    		return maxPrice;
    	}
    	Double d =Double.valueOf(increase)/100;
    	return maxPrice.multiply(BigDecimal.valueOf(d)).add(maxPrice);
}

	/**
     * @Title: addPlatFormProduct
     * @Description:添加平台商品
     * @param @param source
     * @param @return 参数 商品数据
     * @return Map<String,Object> 返回类型
     * @throws
     */
    @RequestMapping("/addPlatFormProduct")
    public String addPlatFormProduct(ProductDTO product, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        return "";

    }

   /**
    * 编辑商品
   * @param @param  ProductDTO
   * @param @param model
   * @param @return    参数
   * @return  String  返回类型
   * @throws
    */
    @RequestMapping("/updateSelfProduct")
    public @ResponseBody
    String updateSelfProduct(@RequestBody ProductDTO product, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        ProductPO pro = productConvert.convertToDomain(product);
        pro.setUpdateName(baseSessionUserDTO.getNickName());
        pro.setSellerId(baseSessionUserDTO.getUserId());
        pro.setShopId(baseSessionUserDTO.getShopId());
        List<ProductSpecDTO> specList = product.getSepcList();
        List<ProductSpecPO> spList = new ArrayList<ProductSpecPO>();
        for (ProductSpecDTO productSpecDTO : specList) {
            ProductSpecPO p = new ProductSpecPO();
            p.setSpecName(productSpecDTO.getSpecName());
            p.setStock(productSpecDTO.getStock());
            p.setSalesPrice(productSpecDTO.getSalesPrice());
            if (productSpecDTO.getId() != null && !productSpecDTO.getId().isEmpty()) {
                p.setId(Integer.valueOf(productSpecDTO.getId()));
            }
            spList.add(p);
        }
        int i = productService.updateSelfProduct(pro, spList,product.getImageList());
        if (i > 0) {
            return "success";
        }
        return null;

    }

    /**
     * @Title: upStateUpdate
     * @Description: 上架 下架
     * @param @param productId 商品ID state 状态
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/upStateUpdate")
    public @ResponseBody String upStateUpdate(Integer productId, Integer state, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ProductPO product = new ProductPO();
        product.setUpdateTime(new Date());
        product.setUpState(state);
        if (state == 1) {
            product.setUpTime(new Date());
        }
        product.setUpdateName(baseSessionUserDTO.getNickName());
        int i = productService.upStateUpdate(product, productId);
        if (i > 0) {
            return "success";
        }
        return "";

    }

    /**
     * @Title: upStateUpdate
     * @Description: 上架 下架(批量)
     * @param @param productId 商品ID state 状态
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/upStateUpdates")
    public @ResponseBody String upStateUpdates(@RequestBody ParamDTO param, HttpServletRequest request,
            HttpServletResponse response) {
        Integer state =param.getState();
        List<Integer> productIds = param.getIds();
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        List<ProductPO> lists = new ArrayList<ProductPO>();
        for (Integer productId : productIds) {
            ProductPO product = new ProductPO();
            product.setUpdateTime(new Date());
            product.setUpState(state);
            product.setUpdateName(baseSessionUserDTO.getNickName());
            if (state == 1) {
                product.setUpTime(new Date());
            }
            product.setId(productId);
            lists.add(product);
        }

        int i = productService.batchUpStateUpdate(lists);
        return "success";

    }

    /**
     * 商品分组管理
     * 
     * @Description: TODO
     * @param @param productId
     * @param @param groupId
     * @param @param request
     * @param @param response
     * @param @return 设定文件
     */
    @RequestMapping("/changeProductGroup")
    public String changeProductGroup(Integer productId, Integer groupId, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ProductPO product = new ProductPO();
        product.setId(productId);
        product.setUpdateTime(new Date());
        product.setUpdateName(baseSessionUserDTO.getNickName());
        product.setGroupId(groupId);
        int i = productService.updateProductGroup(product);
        if (i > 0) {
            return "/product/initList";
        }
        return null;

    }

    /**
     * @Description: 卖家看到的商品列表
     * @param @param upstate 上下架状态
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @SuppressWarnings({"unchecked", "rawtypes", "rawtypes"})
    @RequestMapping("/fcgProList")
    public @ResponseBody
    Map fcgProList(ProductPO product, String pageIndex, HttpServletRequest request, HttpServletResponse response) {
        Map result = new HashMap();
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfo =  userInfoService.findById(baseSessionUserDTO.getUserId());
        Page page1 = fcgService.findFcgProductListByPage(userInfo.getFcgSellerId(), userInfo.getFcgToken(), product.getFcgCategoryId(), Integer.valueOf(pageIndex), 5, "");
        
        if (page1 == null) {
            result.put("products", 0);
        }
        else {
            result.put("products", page1.getRows());
        }
        result.put("groupId", product.getGroupId());
        result.put("upState", product.getUpState());
        product.setIsDelete(0);
        result.put("totalCount", productService.countProductList(product));
        product.setUpState(null);
        result.put("totalCountAll", productService.countProductList(product));
        return result;

    }
    /**
     * @Description: 卖家看到的商品列表
     * @param @param upstate 上下架状态
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @SuppressWarnings({"unchecked", "rawtypes", "rawtypes"})
    @RequestMapping("/mylist")
    public @ResponseBody
    Map mylist(ProductPO product, String pageIndex, HttpServletRequest request, HttpServletResponse response) {
        Map result = new HashMap();
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return null;
        }
        product.setSellerId(baseSessionUserDTO.getUserId());
        product.setShopId(baseSessionUserDTO.getShopId());
        List<ProductPO> products = productService.findProductList(product, page);
        List<Integer> productIds = new ArrayList<Integer>();
        for (ProductPO productPO : products) {
            productIds.add(productPO.getId());
        }
        // 组合规格
        List<ProductSpecPO> specLists = productSpecService.getByProductIds(productIds);
        List<ProductDTO> dtoList = productConvert.convertCollectionToDTO(products);
        for (ProductDTO product1 : dtoList) {
            List<ProductSpecPO> list = Lists.newArrayList();
            for (ProductSpecPO spec : specLists) {
                if (product1.getId() == spec.getProductId()) {
                    list.add(spec);
                }
                // 库存
                int stocks = 0;
                for (ProductSpecPO productSpecPO : list) {
                    stocks += productSpecPO.getStock();
                }
                product1.setStocks(stocks);
                product1.setSepcList(productSpecConvert.convertCollectionToDTO(list));
            }
        }
        if (product == null || products.isEmpty()) {
            result.put("products", 0);
        }
        else {
            result.put("products", dtoList);
        }
        result.put("groupId", product.getGroupId());
        result.put("upState", product.getUpState());
        product.setIsDelete(0);
        result.put("totalCount", productService.countProductList(product));
        product.setUpState(null);
        result.put("totalCountAll", productService.countProductList(product));
        return result;

    }

    /**
     * @Description: 初始化商品列表
     * @param @param model
     * @param @param request
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/initList", method = RequestMethod.GET)
    public String initList(Model model, HttpServletRequest request) {
        model.addAttribute("upState", request.getParameter("upState"));
        String groupId ="";
        if(request.getParameter("groupId")!=null){
            groupId =request.getParameter("groupId");
            model.addAttribute("groupId",groupId);
        }
        return "/product/mylist";
    }

    /**
     * 编辑商品
     * 
     * @param @param 商品ID
     * @throws
     */
    @RequestMapping(value = "/preEdit", method = RequestMethod.GET)
    public String preEdit(Integer id, Model model, HttpServletRequest request) {
        if (id == null || id == 0) {
            return null;
        }
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ProductPO product = productService.getById(id);
        ProductDTO productDto = productConvert.convertToDTO(product);
        List<ProductImagePO> imgList  = productImageService.getByProductId(id);
        List<String> imgUrls = Lists.newArrayList();
        for (ProductImagePO productImagePO : imgList) {
			imgUrls.add(productImagePO.getImgUrl());
		}
        UserInfoPO userInfo =  userInfoService.findById(baseSessionUserDTO.getUserId());
        //获取品类
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
        model.addAttribute("fcgCates", JSONArray.fromObject(dtoList));
        productDto.setImageList(imgList);
        productDto.setImgUrlList(imgUrls);
        ProductGroupPO group = productGroupService.getById(product.getGroupId());
        productDto.setGroup(groupConvert.convertToDTO(group));
        productDto.setSepcList(productSpecConvert.convertCollectionToDTO(productSpecService.getByProductId(id)));
        model.addAttribute("product", productDto);
        List<ProductGroupPO> groupList = productGroupService.findByShopId(baseSessionUserDTO.getShopId());
        model.addAttribute("groupList", groupList);
        return "/product/edit";
    }

    /**
     * 编辑商品
     * 
     * @param @param 商品ID
     * @throws
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String delete(@RequestBody ParamDTO param, Model model, HttpServletRequest request) {
        List<Integer> ids =param.getIds();
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        List<ProductPO> lists = new ArrayList<ProductPO>();
        for (Integer productId : ids) {
            ProductPO product = new ProductPO();
            product.setUpdateTime(new Date());
            product.setUpdateName(baseSessionUserDTO.getNickName());
            product.setId(productId);
            product.setIsDelete(1);
            lists.add(product);
        }
        int i = productService.batchDelete(lists);
        return "success";

    }
    

}
