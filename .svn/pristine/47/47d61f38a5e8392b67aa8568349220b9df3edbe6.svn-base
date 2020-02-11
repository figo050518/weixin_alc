package com.fcgo.weixin.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;

@Controller
@RequestMapping("/uc/seller/manage")
public class SellerAccountController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpSession session) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        model.addAttribute("userName", baseSessionUserDTO.getNickName());
        return "/user/sellerMangeIndex";
    }

}
