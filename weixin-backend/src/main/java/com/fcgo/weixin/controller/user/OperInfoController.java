package com.fcgo.weixin.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.dom4j.tree.BackedList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.swing.BakedArrayList;

import com.fcgo.weixin.application.user.OperInfoService;
import com.fcgo.weixin.application.user.OperLoginService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.user.UserSessionInfoService;
import com.fcgo.weixin.common.codec.MD5EncrypterUtil;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.dto.OperInfoDTO;
import com.fcgo.weixin.persist.po.OperInfoPO;
import com.fcgo.weixin.persist.po.OperLoginPO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;
import com.fcgo.weixin.uitl.BackendUtils;
import com.fcgo.weixin.uitl.PaginationContext;

@Controller
@RequestMapping("/oper")
public class OperInfoController {

    @Autowired
    private OperInfoService operInfoService;
    
    @Autowired
    private OperLoginService operLoginService;

    /**
     * 运营人员列表
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/operList")
    public String operList(Model model, HttpServletRequest request, HttpServletResponse response,
    		OperInfoPO operInfoPO) {
    	String pageIndex = request.getParameter("pageIndex");
        if (StringUtils.isEmpty(pageIndex)) {
            pageIndex = "1";
        }
        
        Page page = operInfoService.queryOperInfoListPage(operInfoPO, Integer.parseInt(pageIndex));
        model.addAttribute("seachInfo", operInfoPO);
        model.addAttribute("paginationContext",new PaginationContext(page));
        return "/sys/oper/opers";
    }
    /**
     * 运营人员详情
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/operDetail")
    public String operDetail(Model model, HttpServletRequest request, HttpServletResponse responseoperInfoPO) {
    	String operId = request.getParameter("operId");
        if(StringUtils.isEmpty(operId)){
        	return "/sys/oper/opers";
        }
        OperInfoPO operInfoPO = operInfoService.queryOperInfoById(operId);
        model.addAttribute("operInfo", operInfoPO);
        return "/sys/oper/oper";
    }
    /**
     * 运营人员 启/禁用
     * 
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/operInfoActive")
    public @ResponseBody String operInfoActive(HttpServletRequest request,OperInfoPO operInfoPO) throws Exception {
    	JSONObject resultJson = new JSONObject();
    	try {
			resultJson.put("returnCode", "SUCCESS");
			resultJson.put("resultCode", "SUCCESS");
			if(StringUtils.isEmpty(operInfoPO.getOperId())){
				resultJson.put("returnCode", "ERROR");
				resultJson.put("resultMsg", "参数operId不能为空！");
				return resultJson.toString();
			}
			int type =operInfoPO.getIsDelete();
			//查询
			operInfoPO = operInfoService.queryOperInfoById(operInfoPO.getOperId());
			if(operInfoPO == null){
				resultJson.put("resultCode", "ERROR");
				resultJson.put("resultMsg", "用户信息不存在！");
				return resultJson.toString();
			}
			//更新
			operInfoPO.setIsDelete(type);
			operInfoPO.setUpdateTime(new Date());
			operInfoPO.setUpdateName(BackendUtils.getLoginUserName(request));
			boolean isSuccess = operInfoService.updateOperInfo(operInfoPO);
			resultJson.put("resultMsg", "操作成功!");
		} catch (JSONException e) {
			resultJson.put("returnCode", "ERROR");
			resultJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}
        
        return resultJson.toString();
    }
    /**
     * 跳转修改密码页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/toResetPassword")
    public String toResetPassword(Model model, HttpServletRequest request, HttpServletResponse response,
    		OperInfoPO operInfoPO) {
        if(operInfoPO.getOperId() == null){
        	return "redirect:/oper/operList.do";
        }
        operInfoPO = operInfoService.queryOperInfoById(operInfoPO.getOperId());
        if(operInfoPO == null){
        	return "redirect:/oper/operList.do";
        }
        model.addAttribute("operInfo", operInfoPO);
        return "/sys/oper/resetPassword";
    }
    /**
     * 跳转新增页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/toOperInfoAdd")
    public String toOperInfoAdd(Model model, HttpServletRequest request, HttpServletResponse response,
    		OperInfoPO operInfoPO) {
        return "/sys/oper/operAdd";
    }
    /**
     * 修改密码
     * 
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "resetPassword")
    @ResponseBody
    public String resetPassword(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	JSONObject resultJson = new JSONObject();
    	
    	try {
    		resultJson.put("returnCode", "SUCCESS");
    		resultJson.put("resultCode", "SUCCESS");
    		resultJson.put("resultMsg", "修改成功!");
    		
            String operId = request.getParameter("operId");
            String newpassword = ObjectUtils.toString(request.getParameter("newpassword"));
            String newpassword2 = ObjectUtils.toString(request.getParameter("newpassword2"));
            
            if(StringUtils.isEmpty(operId)){
            	resultJson.put("resultCode", "ERROR");
            	resultJson.put("resultMsg", "账号不能为空!");
            	return resultJson.toString();
            }
            
            if(!newpassword.equals(newpassword2)){
            	resultJson.put("resultCode", "ERROR");
            	resultJson.put("resultMsg", "两次密码输入不一致!");
            	return resultJson.toString();
            }
            OperLoginPO operLoginPO = operLoginService.getOperLoginById(operId);
            if(operLoginPO == null){
            	resultJson.put("resultCode", "ERROR");
            	resultJson.put("resultMsg", "没有用户登录信息!");
            	return resultJson.toString();
            }
            operLoginPO.setPassword(MD5EncrypterUtil.md5PwdEncrypt(newpassword));
            operLoginPO.setUpdateName(BackendUtils.getLoginUserName(request));
            operLoginPO.setUpdateTime(new Date());
            
            operLoginService.updateOperLogin(operLoginPO);
    	}catch(Exception e){
			resultJson.put("resultCode", "ERROR");
			resultJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
    	}
    	
        return resultJson.toString();
    }
    
    /**
     * 保存用户信息
     * 
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "saveOperInfo")
    @ResponseBody
    public String saveOperInfo(HttpServletRequest request, HttpServletResponse response,OperInfoPO operInfoPO,OperLoginPO operLoginPO) throws Exception {
    	JSONObject resultJson = new JSONObject();
    	
    	try {
    		resultJson.put("returnCode", "SUCCESS");
    		resultJson.put("resultCode", "SUCCESS");
    		resultJson.put("resultMsg", "保存成功!");
            
    		operInfoPO.setCreateName(BackendUtils.getLoginUserName(request));
    		operInfoPO.setCreateTime(new Date());
    		if(operInfoPO.getId() == null){//新增
    			if(StringUtils.isEmpty(operLoginPO.getPassword())){
    				resultJson.put("resultCode", "ERROR");
    	    		resultJson.put("resultMsg", "密码不能为空!");
    	    		return resultJson.toString();
    			}
    			if(StringUtils.isEmpty(operInfoPO.getOperId())){
    				resultJson.put("resultCode", "ERROR");
    	    		resultJson.put("resultMsg", "用户名不可为空!");
    	    		return resultJson.toString();
    			}
    		
    			boolean isExists = operInfoService.validateUniqueOperInfo(operInfoPO);
    			if(isExists){//已存在
    				resultJson.put("resultCode", "ERROR");
    	    		resultJson.put("resultMsg", "用户已存在，不可新增!");
    	    		return resultJson.toString();
    			}
    			//运营人员信息
    			operInfoPO.setCreateName(BackendUtils.getLoginUserName(request));
    			operInfoPO.setCreateTime(new Date());
    			operInfoPO.setIsDelete(0);
    			operInfoPO.setUpdateName(BackendUtils.getLoginUserName(request));
    			operInfoPO.setUpdateTime(new Date());
    			
    			//登录信息
    			operLoginPO.setCreateName(BackendUtils.getLoginUserName(request));
    			operLoginPO.setCreateTime(new Date());
    			operLoginPO.setIsDelete(0);
    			operLoginPO.setUpdateName(BackendUtils.getLoginUserName(request));
    			operLoginPO.setUpdateTime(new Date());
    			
    			String password = operLoginPO.getPassword();
    			operLoginPO.setPassword(MD5EncrypterUtil.md5PwdEncrypt(password));
    			
    			
    			//保存
    			operLoginService.saveOperLogin(operLoginPO);
    			operInfoService.saveOperInfo(operInfoPO);
    			
    		}else{
    			operInfoPO.setUpdateName(BackendUtils.getLoginUserName(request));
    			operInfoPO.setUpdateTime(new Date());
    			operInfoService.saveOperInfo(operInfoPO);
    		}
            
    	}catch(Exception e){
			resultJson.put("resultCode", "ERROR");
			resultJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
    	}
    	
        return resultJson.toString();
    }
}
