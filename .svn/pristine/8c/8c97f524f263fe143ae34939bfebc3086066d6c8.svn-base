package com.fcgo.weixin.controller.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.product.IProductGroupService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.controller.product.convert.ProductGroupConvert;
import com.fcgo.weixin.dto.ProductGroupDTO;
import com.fcgo.weixin.persist.po.ProductGroupPO;
import com.google.common.collect.Lists;

/**
* @ClassName: PdtProductGroupController 
* @Description: 商品分组
* @author zhonghui.geng
* @date 2017年4月7日 下午4:39:11 
*
 */
@Controller
@RequestMapping("/uc/productGroup")
public class PdtProductGroupController {
    @Autowired
    private IProductGroupService productGroupService;
    @Autowired
    private ProductGroupConvert groupConvert;

   
    @RequestMapping(value="/list",method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String,Object> list( @RequestParam(required=false,value="needCount") boolean needCount,String pageIndex,HttpServletRequest request, HttpServletResponse response) {
        Map result = new HashMap();
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if(baseSessionUserDTO==null){
            return null;
        }
        List<ProductGroupDTO> groupDtoList = Lists.newArrayList();
        if(!needCount){
            List<ProductGroupPO> groupList = productGroupService.findByShopId(baseSessionUserDTO.getShopId());
             groupDtoList = groupConvert.convertCollectionToDTO(groupList);
        }else{
            List<ProductGroupPO> groupList = productGroupService.findByShopIdWithCount(baseSessionUserDTO.getShopId(),page);
            groupDtoList = groupConvert.convertCollectionToDTO(groupList);
        }
        result.put("groups", groupDtoList);
        return result;

    }
    
    @RequestMapping(value="/initGroupList",method = RequestMethod.GET)
    public String initGroupList(HttpServletRequest request, HttpServletResponse response) {
        return "/product/pdtClassify";

    }
    
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public @ResponseBody ProductGroupDTO add(String name, HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if(baseSessionUserDTO==null){
            return null;
        }
        ProductGroupPO p = new ProductGroupPO();
        p.setGroupName(name);
        p.setSellerId(baseSessionUserDTO.getUserId());
        p.setShopId(baseSessionUserDTO.getShopId());
        p.setCreateName(baseSessionUserDTO.getNickName());
        p.setUpdateName(baseSessionUserDTO.getNickName());
        int i = productGroupService.add(p);
        if(i>0){
           return  groupConvert.convertToDTO(p);
        }
        return null;

    }
    @RequestMapping(value="/delete",method = RequestMethod.GET)
    public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if(baseSessionUserDTO==null){
            return null;
        }
        productGroupService.delete(id);
        
        return null;

    }
    @RequestMapping(value="/update",method = RequestMethod.GET)
    public @ResponseBody List<ProductGroupDTO> add(String name,Integer id, HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if(baseSessionUserDTO==null){
            return null;
        }
        ProductGroupPO group = new ProductGroupPO();
        group.setGroupName(name);
        group.setId(id);
        group.setUpdateTime(new Date());
        group.setUpdateName(baseSessionUserDTO.getNickName());
        int i =productGroupService.update(group);
       /* if(i>0){
           return  this.list(false,"0", request, response);
        }*/
        return null;

    }
    
    
    
    
    
    
}
