package com.fcgo.weixin.controller.system.user;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.dto.UserLoginDTO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

/**
 * 用户注册
 * 
 * @author xiahanxzh
 */
@RequestMapping("/user")
@Controller
public class UserResgisterController {

    @Autowired
    private UserInfoService userRegisterService;

    /**
     * 注册初始化
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerInit(Model model, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        String backUrl = getBackUrl(model, request, headers);
        model.addAttribute("backUrl", backUrl);
        return "/user/register";
    }

    /**
     * 提交注册
     * 
     * @param model
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    String register(Model model, @RequestHeader HttpHeaders headers, HttpServletRequest request,
            UserLoginDTO userLoginDTO) throws IllegalAccessException, InvocationTargetException {
        // 判断手机号和验证码是否合法
        String message = userRegisterService.validateTelephone(userLoginDTO.getTelephone(), userLoginDTO.getCode());
        if (!message.equals("success")) {
            return message;
        }
        UserLoginPO userLoginPO = new UserLoginPO();
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUserType(Integer.valueOf(userLoginDTO.getUserType()));
        BeanUtils.copyProperties(userLoginPO, userLoginDTO);
        boolean result = userRegisterService.register(userLoginPO, userInfoPO);
        if (result) {
            message = "success";
        }
        return message;
    }

    private String getBackUrl(Model model, HttpServletRequest request, HttpHeaders headers) {
        Map<String, Object> map = model.asMap();
        // 获取需求登录的URL
        String backUrl =
                (String) (request.getParameter("backUrl") == null ? map.get("backUrl") : request
                        .getParameter("backUrl"));
        if (StringUtils.isBlank(backUrl)) {
            // 获取来源页面
            String refererUrl = getReferer(headers);
            // 无来源页面，登录成功后进入各自的会员中心
            if (StringUtils.isBlank(refererUrl)) {
                // 跳转到各自的用户中心
                backUrl = "/uc/index";
            }
            else {
                backUrl = "/uc/index";;
            }
        }
        return backUrl;
    }

    /**
     * 获取Referer
     * 
     * @param headers
     * @return
     */
    protected String getReferer(HttpHeaders headers) {
        return headers.getFirst("Referer");
    }

}
