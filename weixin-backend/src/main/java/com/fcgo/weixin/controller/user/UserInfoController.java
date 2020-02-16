package com.fcgo.weixin.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.uitl.BackendUtils;
import com.fcgo.weixin.uitl.PaginationContext;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户信息查询
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/listUsers")
    public String listUsers(Model model, HttpServletRequest request,UserInfoPO userInfoPO) {
    	String pageIndex = request.getParameter("pageIndex");
    	Page page = new Page();
        if (StringUtils.isEmpty(pageIndex)) {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        
        List datas = userInfoService.listUsers(userInfoPO, page);
        model.addAttribute("userInfos", datas);
        model.addAttribute("seachInfo", userInfoPO);
        model.addAttribute("paginationContext",new PaginationContext(page));
        return "/user/users";
    }
    /**
     * 用户信息查询
     * 
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/userActive")
    public @ResponseBody String userActive(Model model, HttpServletRequest request,UserInfoPO userInfoPO) throws Exception {
    	JSONObject resultJson = new JSONObject();
    	try {
			resultJson.put("returnCode", "SUCCESS");
			resultJson.put("resultCode", "SUCCESS");
			if(userInfoPO.getId()==null || userInfoPO.getId()==0){
				resultJson.put("returnCode", "ERROR");
				resultJson.put("resultMsg", "参数id不能为空！");
				return resultJson.toString();
			}
			int type =userInfoPO.getIsDelete();
			//查询
			userInfoPO = userInfoService.findById(userInfoPO.getId());
			if(userInfoPO == null){
				resultJson.put("returnCode", "ERROR");
				resultJson.put("resultMsg", "用户信息不存在！");
				return resultJson.toString();
			}
			//更新
			userInfoPO.setIsDelete(type);
			userInfoPO.setUpdateTime(new Date());
			userInfoPO.setUpdateName(BackendUtils.getLoginUserName(request));
			boolean isSuccess = userInfoService.userActive(userInfoPO);
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
