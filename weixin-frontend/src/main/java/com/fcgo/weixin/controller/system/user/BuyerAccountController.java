package com.fcgo.weixin.controller.system.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.codec.MD5EncrypterUtil;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.SignInUtils;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

@Controller
@RequestMapping("/uc/buyer/manage")
public class BuyerAccountController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpSession session) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfoPO = userInfoService.findById(baseSessionUserDTO.getUserId());
        model.addAttribute("userName", userInfoPO.getNikeName());
        return "/user/accountSettings";
    }

    @RequestMapping(value = "/changerPassword", method = RequestMethod.GET)
    public String changerPasswordInit(Model model, HttpServletRequest request, HttpSession session) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfoPO = userInfoService.findById(baseSessionUserDTO.getUserId());
        model.addAttribute("telephone", userInfoPO.getTelNum());
        return "/user/changePassword";
    }

    @RequestMapping(value = "/changerPassword", method = RequestMethod.POST)
    public @ResponseBody
    String changerPassword(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String password) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserLoginPO userLoginPO = new UserLoginPO();
        userLoginPO.setUserId(baseSessionUserDTO.getUserId());
        userLoginPO.setPassword(MD5EncrypterUtil.md5PwdEncrypt(password));
        userLoginPO.setUpdateTime(new Date());
        userInfoService.changePassword(userLoginPO);
        // 注销用户
        SignInUtils.aizhuSignOut(request, response);
        // 注销会话
        HttpSessionProvider.logout(request, response);
        return "success";
    }

    @RequestMapping(value = "/changeNickName", method = RequestMethod.GET)
    public String changeNickNameInit(Model model, HttpServletRequest request, HttpSession session) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfoPO = userInfoService.findById(baseSessionUserDTO.getUserId());
        model.addAttribute("userName", userInfoPO.getNikeName());
        return "/user/changenickName";
    }

    @RequestMapping(value = "/changeNickName", method = RequestMethod.POST)
    public String changeNickName(Model model, HttpServletRequest request, HttpSession session, String userName) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setId(baseSessionUserDTO.getUserId());
        userInfoPO.setNikeName(userName);
        userInfoPO.setUpdateTime(new Date());
        userInfoService.changeNickName(userInfoPO);
        return "redirect:/uc/buyer/manage/index";
    }

    @RequestMapping(value = "/changeTelephone", method = RequestMethod.GET)
    public String changeTelephoneInit(Model model, HttpServletRequest request, HttpSession session) {
        return "/user/changeMobile";
    }

    @RequestMapping(value = "/changeTelephone", method = RequestMethod.POST)
    public String changeTelephone(Model model, HttpServletRequest request, HttpSession session, String telephone,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserInfoPO userInfoPO = new UserInfoPO();
        UserLoginPO userLoginPO = new UserLoginPO();
        userLoginPO.setUserId(baseSessionUserDTO.getUserId());
        userInfoPO.setId(baseSessionUserDTO.getUserId());
        userInfoPO.setTelNum(telephone);
        userLoginPO.setTelephone(telephone);
        userLoginPO.setUpdateTime(new Date());
        userInfoService.changeTelephone(userInfoPO, userLoginPO);
        // 注销用户
        SignInUtils.aizhuSignOut(request, response);
        // 注销会话
        HttpSessionProvider.logout(request, response);
        return "redirect:/uc/index";
    }

}
