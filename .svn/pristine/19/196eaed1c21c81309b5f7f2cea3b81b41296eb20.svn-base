package com.fcgo.weixin.controller.shop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;
import com.fcgo.weixin.application.interfaces.fcg.IFcgService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.log.LogCategory;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.controller.shop.convert.SellerShopConvert;
import com.fcgo.weixin.dto.SellerShopDTO;
import com.fcgo.weixin.persist.po.SellerShopPO;
import com.fcgo.weixin.persist.po.UserInfoPO;

@Controller
@RequestMapping(value = "/uc/shop")
public class SellerShopController {

    @Autowired
    private SellerShopService sellerShopService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SellerShopConvert sellerShopConvert;

    @Autowired
    private IFcgService fcgService;

    private Log logger = LogFactory.getLog(LogCategory.CONTROLLER.toString());

    @ModelAttribute("SellerShop")
    public SellerShopDTO get(@RequestParam(required = false) String id, HttpServletRequest request) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (id == null || id.isEmpty()) {
            id = String.valueOf(baseSessionUserDTO.getShopId());
        }
        if (!StringUtils.isEmpty(id)) {
            return sellerShopConvert.convertToDTO(sellerShopService.findById(id));
        }
        else {
            return new SellerShopDTO();
        }
    }

    /**
     * @throws UnsupportedEncodingException
     * @Title: openShop @Description: 开设店铺 @param fcgUserId 非常购 用户ID（目测用于鉴定用户是否有权限开店，暂定） @param @return @return 返回类型 @throws
     */

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public String openShop(String fcgUserId, String token, HttpServletRequest request, HttpServletResponse response,
            Model model) throws UnsupportedEncodingException {
        // step2 判断用户是否已经开过店
        UserInfoPO userInfo = userInfoService.findByFcgId(fcgUserId);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);;
            BaseSessionUserDTO baseSessionUserDTO =
                    new BaseSessionUserDTO(userInfo.getTelNum(), userInfo.getNikeName(), false, true, userInfo.getId(),
                            sellerShopService.findByParam(null, Integer.valueOf(userInfo.getId()).toString()).getId());
            initSignInInfo(request, response, baseSessionUserDTO);
            return "/uc/index";
        }
        model.addAttribute("fcgUserId", fcgUserId);
        model.addAttribute("token", token);
        return "/shop/openShop";
    }

    /**
     * @throws UnsupportedEncodingException
     * @Title: save @Description: 保存店铺 @param @param sellerShopDTO @param @param file 图片文件 @throws
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SellerShopDTO sellerShopDTO, HttpServletRequest request, HttpServletResponse response,
            Model model) throws UnsupportedEncodingException {
        // 通过fcgUserId 请求第三方接口获取用户信息，暂时写死
        SellerShopPO shop = new SellerShopPO();
        shop.setShopName(sellerShopDTO.getShopName());
        shop.setShopDesc(sellerShopDTO.getShopDesc());
        shop.setLogoUrlId(sellerShopDTO.getBgUrl());
        shop.setBgUrlId(sellerShopDTO.getBgUrl());
        BaseSessionUserDTO baseSessionUserDTO =
                sellerShopService.save(shop, sellerShopDTO.getFcgUserId(), sellerShopDTO.getToken());
        initSignInInfo(request, response, baseSessionUserDTO);
        return "redirect:/uc/index";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        SellerShopPO sellerShop =
                sellerShopService.findByParam(String.valueOf(baseSessionUserDTO.getShopId()),
                        String.valueOf(baseSessionUserDTO.getUserId()));
        if (sellerShop == null) {
            return "/shop/open";
        }
        model.addAttribute("sellerShop", sellerShopConvert.convertToDTO(sellerShop));
        return "/shop/shopDetail";
    }

    @RequestMapping(value = "/preUpdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        SellerShopPO sellerShop = sellerShopService.findById(String.valueOf(baseSessionUserDTO.getShopId()));
        if (sellerShop != null) {
            model.addAttribute("sellerShop", sellerShopConvert.convertToDTO(sellerShop));
        }
        return "/shop/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SellerShopDTO sellerShop, HttpServletRequest request, HttpServletResponse response, Model model) {
        SellerShopPO sellerShop2 = sellerShopConvert.convertToDomain(sellerShop);
        int i = sellerShopService.update(sellerShop2);
        if (i > 0) {
            return "redirect:/uc/shop/view";
        }
        return null;
    }

    public void initSignInInfo(HttpServletRequest request, HttpServletResponse response,
            BaseSessionUserDTO baseSessionUserDTO) throws UnsupportedEncodingException {
        HttpSessionProvider.setAttribute(request, response, "session_attr_user", baseSessionUserDTO);
        // 设置session失效时间
        request.getSession().setMaxInactiveInterval(60 * 60 * 24);
        // 添加userName到cookie中
        CookieUtils.addCookie(request, response, CookieUtils.USER_NAME_COOKIE_NAME,
                URLEncoder.encode(baseSessionUserDTO.getNickName(), "UTF-8"), 30 * 24 * 60 * 60, ".fcg.com");
        CookieUtils.addCookie(request, response, CookieUtils.IS_BUYER, baseSessionUserDTO.getIsBuyer().toString(),
                30 * 24 * 60 * 60, ".fcg.com");
        CookieUtils.addCookie(request, response, CookieUtils.IS_SELLER, baseSessionUserDTO.getIsSeller().toString(),
                30 * 24 * 60 * 60, ".fcg.com");
        // shopCartService.mergeShopCart(userInfoDTO, response, request);
    }

}
