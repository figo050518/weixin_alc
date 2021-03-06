package com.fcgo.weixin.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.ISellerCustomerService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.constants.OrderStateConstants;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.SellerCustomerDTO;
import com.fcgo.weixin.persist.po.SellerCustomerPO;
import com.fcgo.weixin.persist.po.UserInfoPO;

/**
 * 卖家客户统计
 * 
 * @author Ww
 */
@Controller
@RequestMapping("/uc/SellerCustomer")
public class SellerCustomerController {

    @Autowired
    private ISellerCustomerService sellerCustomerService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 分页查询卖家客户
     * 
     * @param model
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findByPage")
    public String findByPage(Model model, String pageIndex, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // BaseSessionUserDTO baseSessionUserDTO = new BaseSessionUserDTO(null, null, true, false, 1, 1);
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        Page page = sellerCustomerService.findCustomeLsitBySellerId(baseSessionUserDTO.getUserId(),
                Integer.parseInt(pageIndex));
        @SuppressWarnings("unchecked")
        List<SellerCustomerPO> sellerCustomerPOs = (List<SellerCustomerPO>) page.getRows();
        List<SellerCustomerDTO> sellerCustomerDTOs = new ArrayList<SellerCustomerDTO>();
        for (SellerCustomerPO sellerCustomerPO : sellerCustomerPOs) {
            SellerCustomerDTO sellerCustomerDTO = new SellerCustomerDTO();
            UserInfoPO userInfoPO = userInfoService.findById(sellerCustomerPO.getUserId());
            sellerCustomerDTO.setUserInfoPO(userInfoPO);
            sellerCustomerDTO.setSumMoey(sellerCustomerPO.getSumMoney());
            sellerCustomerDTOs.add(sellerCustomerDTO);
        }
        page.setRow(sellerCustomerDTOs);
        model.addAttribute("sellerId", baseSessionUserDTO.getUserId());
        model.addAttribute("page", page);
        return "/user/sellerCustomerList";
    }

    /**
     * ajax分页查询卖家客户
     * 
     * @param model
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findByPageAjax")
    @ResponseBody
    public Map<String, Object> findByPageAjax(String pageIndex, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // BaseSessionUserDTO baseSessionUserDTO = new BaseSessionUserDTO(null, null, true, false, 1, 1);
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        Page page = sellerCustomerService.findCustomeLsitBySellerId(baseSessionUserDTO.getUserId(),
                Integer.parseInt(pageIndex));
        @SuppressWarnings("unchecked")
        List<SellerCustomerPO> sellerCustomerPOs = (List<SellerCustomerPO>) page.getRows();
        List<SellerCustomerDTO> sellerCustomerDTOs = new ArrayList<SellerCustomerDTO>();

        for (SellerCustomerPO sellerCustomerPO : sellerCustomerPOs) {
            SellerCustomerDTO sellerCustomerDTO = new SellerCustomerDTO();
            UserInfoPO userInfoPO = userInfoService.findById(sellerCustomerPO.getUserId());
            sellerCustomerDTO.setUserInfoPO(userInfoPO);
            sellerCustomerDTO.setSumMoey(sellerCustomerPO.getSumMoney());
            sellerCustomerDTOs.add(sellerCustomerDTO);
        }
        page.setRow(sellerCustomerDTOs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sellerId", baseSessionUserDTO.getUserId());
        map.put("page", page);
        return map;
    }

    /**
     * 详细
     * 
     * @param model
     * @param userId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findByDetail")
    public String findByDetail(Model model, String userId, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        SellerCustomerPO sellerCustomerPO =
                sellerCustomerService.findCustomeByDetail(baseSessionUserDTO.getUserId(), Integer.parseInt(userId));

        SellerCustomerDTO sellerCustomerDTO = new SellerCustomerDTO();
        UserInfoPO userInfoPO = userInfoService.findById(sellerCustomerPO.getUserId());
        sellerCustomerDTO.setUserInfoPO(userInfoPO);
        sellerCustomerDTO.setSumMoey(sellerCustomerPO.getSumMoney());
        model.addAttribute("sellerId", baseSessionUserDTO.getUserId());
        model.addAttribute("sellerCustomerDTO", sellerCustomerDTO);
        // 等待发货订单
        int waitShipCount = sellerCustomerService.findCountOrderState(Integer.parseInt(userId),
                baseSessionUserDTO.getUserId(), OrderStateConstants.WAIT_SHIP.getKey());
        model.addAttribute("waitShipCount", waitShipCount);
        // 已发货订单
        int waitConfirmCount = sellerCustomerService.findCountOrderState(Integer.parseInt(userId),
                baseSessionUserDTO.getUserId(), OrderStateConstants.WAIT_CONFIRM.getKey());
        model.addAttribute("waitConfirmCount", waitConfirmCount);
        // 已完成订单
        int finishedCount = sellerCustomerService.findCountOrderState(Integer.parseInt(userId),
                baseSessionUserDTO.getUserId(), OrderStateConstants.FINISHED.getKey());
        model.addAttribute("finishedCount", finishedCount);
        // 已关闭订单
        int canceledCount = sellerCustomerService.findCountOrderState(Integer.parseInt(userId),
                baseSessionUserDTO.getUserId(), OrderStateConstants.CANCELED.getKey());
        model.addAttribute("canceledCount", canceledCount);
        // 累计消费订单总数
        int count = waitConfirmCount + finishedCount + canceledCount + waitShipCount;
        model.addAttribute("count", count);
        return "/user/sellerCustomerDetail";
    }
}
