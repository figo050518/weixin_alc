package com.fcgo.weixin.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

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
import com.fcgo.weixin.application.user.UserLoginService;
import com.fcgo.weixin.application.user.UserSessionInfoService;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.SignInUtils;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserSessionInfoService userSessionInfoService;

    /**
     * 登录初始化
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginInitial(Model model, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        String backUrl = getBackUrl(model, request, headers);
        model.addAttribute("backUrl", backUrl);
        return "/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
            UserLoginPO userLoginPO) throws UnsupportedEncodingException {
        boolean result = userLoginService.validateLogin(userLoginPO);
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断登录是否成功
        if (!result) {
            // 登录失败返回
            map.put("error", "error");
        }
        else {
            // 登录成功查询用户基本信息
            UserInfoPO userInfo = userInfoService.getUserInfoByTelephone(userLoginPO.getTelephone());
            String tokenId = userSessionInfoService.insertSessionInfo(userLoginPO.getTelephone());
            Date signInQBTime = DateUtil.getBJCurrentDate();
            map.put("tokenId", tokenId);
            map.put("telephone", userInfo.getTelNum());
            map.put("userName", userInfo.getNikeName());
            map.put("userType", userInfo.getUserType());
            map.put("userId", userInfo.getId());
            map.put("signInTime", StringUtils.EMPTY + signInQBTime.getTime());
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }

    /**
     * 登录初始化用户信息
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/sign-in-loading", method = RequestMethod.GET)
    public String signInLoadingQB(Model model, String tokenId, UserInfoPO userInfo, HttpServletRequest request,
            HttpServletResponse response, String signInTime, String userId) throws UnsupportedEncodingException {
        List<UserSessionInfoPO> sessionInfos = userSessionInfoService.findBytokenId(tokenId);
        if (sessionInfos != null && sessionInfos.size() > 0) {
            SignInFcgToken signInFcgToken = new SignInFcgToken(tokenId, userInfo.getTelNum(), signInTime);
            // 添加登录时间
            SignInUtils.addQBSignInTime(request, response, signInTime);
            SignInUtils.initCookieInfo(request, response, signInFcgToken);
        }
        return "/user/sign-in-loading-succ";
    }

    /**
     * 忘记密码初始化
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String forgetPasswordInitial(Model model, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        return "/user/forgetPassword";
    }

    /**
     * 忘记密码初始化
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public @ResponseBody
    String forgetPassword(Model model, HttpServletRequest request, String telephone, String password,
            String validateCode) {
        String msg = null;
        try {
            msg = userLoginService.forgotPassword(telephone, password, validateCode);
        }
        catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;

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

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // 注销用户
        SignInUtils.aizhuSignOut(httpServletRequest, httpServletResponse);
        // 注销会话
        HttpSessionProvider.logout(httpServletRequest, httpServletResponse);
        return "redirect:/user/login";
    }

}
