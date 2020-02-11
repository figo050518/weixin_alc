package com.fcgo.weixin.controller.weixin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcgo.weixin.application.weixin.GetWeiXinOpenId;
import com.fcgo.weixin.application.weixin.UserAutoLoginService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;

@Controller
@RequestMapping("/weixin")
public class WeiXinAuthController {

    @Autowired
    private UserAutoLoginService autoLoginService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void list(Model model, HttpServletRequest request, HttpServletResponse httpServletResponse,
            HttpSession session, @RequestHeader HttpHeaders headers, String code) throws IOException {
        Map<String, String> result = GetWeiXinOpenId.getOpenId(code);
        String openId = result.get("openid");
        String nickName = result.get("nickName");
        // 自动登录
        BaseSessionUserDTO baseSessionUserDTO = autoLoginService.autoLogin(openId, nickName);
        HttpSessionProvider.setAttribute(request, httpServletResponse, "session_attr_user", baseSessionUserDTO);
        String backUrl = getBackUrl(model, request, headers);
        httpServletResponse.sendRedirect(backUrl);
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
