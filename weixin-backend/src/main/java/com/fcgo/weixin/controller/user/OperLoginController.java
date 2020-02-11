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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;
import com.fcgo.weixin.uitl.BackendUtils;

@Controller
@RequestMapping("/oper")
public class OperLoginController {

    @Autowired
    private OperLoginService operLoginService;

    @Autowired
    private OperInfoService operInfoService;

    /**
     * 登录初始化
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginIndex(Model model, HttpServletRequest request) {
    	if(BackendUtils.isLogin(request)){
			return "redirect:/oper/index.do";
		} 
        return "/login";
    }
    
    /**
     * 登录
     * 
     * @param model
     * @return
     * @throws JSONException 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login( HttpServletRequest request, HttpServletResponse response,
            OperLoginPO operLoginPO) throws UnsupportedEncodingException, JSONException {
    	JSONObject resultJson = new JSONObject();
    	resultJson.put("returnCode", "SUCCESS");
    	resultJson.put("resultCode", "SUCCESS");
    	
    	if(StringUtils.isEmpty(operLoginPO.getOperId())||StringUtils.isEmpty(operLoginPO.getPassword())){
    		resultJson.put("resultCode", "ERROR");
    		resultJson.put("resultMsg", "用户名或者密码不能为空!");
    		return resultJson.toString();
    	}
        boolean result = operLoginService.validateLogin(operLoginPO.getOperId(),operLoginPO.getPassword());
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断登录是否成功
        if (!result) {
            // 登录失败返回
        	resultJson.put("resultCode", "ERROR");
    		resultJson.put("resultMsg", "用户名或者密码错误!");
    		return resultJson.toString();
        }
        
        
        // 登录成功查询用户基本信息
        OperInfoPO operInfoPO = operInfoService.queryValidOperInfoById(operLoginPO.getOperId());
        if(operInfoPO == null){  // 无用户信息!
        	resultJson.put("resultCode", "ERROR");
    		resultJson.put("resultMsg", "用户不存在，或被禁用!");
    		return resultJson.toString();
        }
        
        OperInfoDTO operInfoDTO = new OperInfoDTO();
        BeanUtils.copyProperties(operInfoPO, operInfoDTO);
        
        BackendUtils.addLoginSession(request, operInfoDTO);
        return resultJson.toString();
    }
    /**
     * 登出
     * 
     * @param model
     * @return
     * @throws JSONException 
     */
    @RequestMapping(value = "/logout")
    String logout( HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, JSONException {
    	BackendUtils.logout(request);
        return "/login";
    }
    
    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "/index";
    }
    /**
     * welcome页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome() {
        return "/welcome";
    }
    
}
