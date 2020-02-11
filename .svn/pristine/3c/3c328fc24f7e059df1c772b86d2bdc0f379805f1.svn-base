package com.fcgo.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;

@Controller
@RequestMapping("/uc")
public class UcIndexController {
    /**
     * 初始化买卖家微店首页
     * 
     * @param model
     * @return
     */
    @Autowired
    private SellerShopService sellerShopService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpSession session) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        model.addAttribute("userName", baseSessionUserDTO.getNickName());
        if (baseSessionUserDTO.getIsSeller()) {
            model.addAttribute("shopName",
                    sellerShopService.findById(Integer.valueOf(baseSessionUserDTO.getShopId()).toString())
                            .getShopName());
            return "/user/sellerUcIndex";
        }
        if (baseSessionUserDTO.getIsBuyer()) {
            return "/user/buyerUcIndex";
        }
        return null;
    }
}
