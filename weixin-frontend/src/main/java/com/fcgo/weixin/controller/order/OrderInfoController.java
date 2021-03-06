package com.fcgo.weixin.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.dto.LogiticsTrackDTO;
import com.fcgo.weixin.application.interfaces.LogisticsTrackService;
import com.fcgo.weixin.application.order.IOrdRefundRequestService;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.application.order.IOrderItemService;
import com.fcgo.weixin.application.order.IOrderReceiverAddressService;
import com.fcgo.weixin.application.user.UserAddressService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.constants.LogisticsCompanyConstants;
import com.fcgo.weixin.common.constants.OrderRefundType;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.OrderInfoListDTO;
import com.fcgo.weixin.dto.OrderItemsDTO;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.OrderItemPO;
import com.fcgo.weixin.persist.po.OrderReceiverAddressPO;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;
import com.fcgo.weixin.persist.po.UserAddressPO;
import com.fcgo.weixin.persist.po.UserInfoPO;

/**
 * 买家/卖家订单类
 * 
 * @author Ww
 */
@Controller
@RequestMapping("/uc/orderInfo")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IOrderReceiverAddressService orderReceiverAddressService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private IOrdRefundRequestService ordRefundRequestService;

    @Autowired
    private LogisticsTrackService logisticsTrackService;

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 初次加载订单列表
     * 
     * @param model
     * @param orderStatus
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getOrderList")
    public String getOrderList(Model model, String orderState, String buyerId, String pageIndex, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // BaseSessionUserDTO baseSessionUserDTO = new BaseSessionUserDTO(null, null, true, false, 1, 1);
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        // 分页数据
        Page page =
                orderInfoService.getOrderInfoList(baseSessionUserDTO, orderState, buyerId, Integer.parseInt(pageIndex));
        // 判断返回数据是否为空
        @SuppressWarnings("unchecked")
        List<OrderInfoPO> orderInfoPOs = (List<OrderInfoPO>) page.getRows();
        // 便利主表获取子表数据
        List<OrderInfoListDTO> infoListDTOs = new ArrayList<OrderInfoListDTO>();
        if (orderInfoPOs != null && orderInfoPOs.size() > 0) {
            for (OrderInfoPO orderInfoPO : orderInfoPOs) {
                List<OrderItemPO> orderItemPOs = orderItemService.getByOrderId(orderInfoPO.getId());
                List<OrderItemsDTO> orderItemsDTOs = new ArrayList<OrderItemsDTO>();
                for (OrderItemPO orderItemPO : orderItemPOs) {
                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderItemPO(orderItemPO);
                    orderItemsDTO.setImageUrl(orderItemPO.getProductPicUrl());
                    orderItemsDTOs.add(orderItemsDTO);
                }
                // 组装返回数据
                OrderInfoListDTO orderInfoListDTO = new OrderInfoListDTO();
                orderInfoListDTO.setOrderInfoPO(orderInfoPO);
                orderInfoListDTO.setOrderItemsDTOs(orderItemsDTOs);
                infoListDTOs.add(orderInfoListDTO);
            }
        }
        page.setRow(infoListDTOs);
        model.addAttribute("page", page);
        if (StringUtils.isBlank(orderState)) {
            orderState = "-1";
        }
        model.addAttribute("orderStatus", orderState);
        model.addAttribute("orderState", orderState);
        // 返回用户身份
        if (baseSessionUserDTO.getIsBuyer()) {
            model.addAttribute("userType", UserType.BUYER.getKey());
        }
        else {
            model.addAttribute("userType", UserType.SELLER.getKey());
        }
        return "/order/orderList";
    }

    /**
     * ajax 分页请求
     * 
     * @param model
     * @param orderStatus
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/orderInfoAjax")
    @ResponseBody
    public Page getOrderListAjax(Model model, String orderState, String buyerId, String pageIndex, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        // 分页数据
        Page page =
                orderInfoService.getOrderInfoList(baseSessionUserDTO, orderState, buyerId, Integer.parseInt(pageIndex));
        // 判断返回数据是否为空
        @SuppressWarnings("unchecked")
        List<OrderInfoPO> orderInfoPOs = (List<OrderInfoPO>) page.getRows();
        // 便利主表获取子表数据
        List<OrderInfoListDTO> infoListDTOs = new ArrayList<OrderInfoListDTO>();
        if (orderInfoPOs != null && orderInfoPOs.size() > 0) {
            for (OrderInfoPO orderInfoPO : orderInfoPOs) {
                List<OrderItemPO> orderItemPOs = orderItemService.getByOrderId(orderInfoPO.getId());
                List<OrderItemsDTO> orderItemsDTOs = new ArrayList<OrderItemsDTO>();
                for (OrderItemPO orderItemPO : orderItemPOs) {
                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderItemPO(orderItemPO);
                    orderItemsDTO.setImageUrl(orderItemPO.getProductPicUrl());
                    orderItemsDTOs.add(orderItemsDTO);
                }
                // 组装返回数据
                OrderInfoListDTO orderInfoListDTO = new OrderInfoListDTO();
                orderInfoListDTO.setOrderInfoPO(orderInfoPO);
                orderInfoListDTO.setOrderItemsDTOs(orderItemsDTOs);
                infoListDTOs.add(orderInfoListDTO);
            }
        }
        page.setRow(infoListDTOs);
        return page;
    }

    /**
     * 根据订单ID查询订单详细
     * 
     * @param orderId
     * @param model
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getByOrderId")
    public String getByOrderId(String orderId, Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 订单主表信息
        OrderInfoPO orderInfoPO = orderInfoService.getByOrderId(Integer.valueOf(orderId));
        orderInfoPO.setLogisticsComp((LogisticsCompanyConstants.getValue(orderInfoPO.getLogisticsComp()).getValue()));
        model.addAttribute("orderInfoPO", orderInfoPO);
        // 子表信息
        List<OrderItemPO> orderItemPOs = orderItemService.getByOrderId(Integer.valueOf(orderId));
        List<OrderItemsDTO> orderItemsDTOs = new ArrayList<OrderItemsDTO>();
        int isTuiHuo = 0;
        for (OrderItemPO orderItemPO : orderItemPOs) {
            OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
            orderItemsDTO.setOrderItemPO(orderItemPO);
            // 判断是否申请售后
            boolean isRefund =
                    orderInfoService.getIsRefund(orderItemPO.getOrderId(), orderItemPO.getProductId(),
                            orderItemPO.getProductSpec());
            if (isRefund) {
                orderItemsDTO.setIsRefund(OrderRefundType.YES_REFUND.getKey());
                // 判断是否是申请退货了
                OrderRefundRequestPO orderRefundRequestPO =
                        ordRefundRequestService.getByOrdIdAndProdId(orderItemPO.getOrderId(),
                                orderItemPO.getProductId(), orderItemPO.getProductSpec());
                if (orderRefundRequestPO.getIsTuihuo() == 1) {
                    isTuiHuo++;
                }
                orderItemsDTO.setOrdRefundId(orderRefundRequestPO.getId());
            }
            else {
                orderItemsDTO.setIsRefund(OrderRefundType.NO_REFUND.getKey());
            }
            // 订单商品图片
            orderItemsDTO.setImageUrl(orderItemPO.getProductPicUrl());
            orderItemsDTOs.add(orderItemsDTO);
        }
        model.addAttribute("orderItemsDTOs", orderItemsDTOs);
        // 收货地址信息
        OrderReceiverAddressPO orderReceiverAddressPO =
                orderReceiverAddressService.getByOrderId(Integer.valueOf(orderId));
        model.addAttribute("orderReceiverAddressPO", orderReceiverAddressPO);

        // 退货地址地址信息
        UserAddressPO userAddressPO = userAddressService.findById(orderInfoPO.getSellerId());
        model.addAttribute("orderRefundAddressPO", userAddressPO);
        // 返回用户身份
        if (baseSessionUserDTO.getIsBuyer()) {
            model.addAttribute("userType", UserType.BUYER.getKey());

        }
        else {
            model.addAttribute("userType", UserType.SELLER.getKey());
        }
        // 卖家基本信息
        UserInfoPO sellerInfo = userInfoService.findById(orderInfoPO.getSellerId());
        model.addAttribute("sellerInfo", sellerInfo);
        // 下单用户基本信息
        UserInfoPO buyerInfo = userInfoService.findById(orderInfoPO.getUserId());
        model.addAttribute("buyerInfo", buyerInfo);
        model.addAttribute("isTuiHuo", isTuiHuo);
        return "/order/orderDetails";
    }

    /**
     * 付款
     * 
     * @param orderId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updatePay")
    public String updatePay(String orderId, String payType, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        orderInfoService.updatePay(Integer.parseInt(orderId), payType, baseSessionUserDTO);
        return "1";
    }

    /**
     * 发货
     * 
     * @param orderId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateSendPro", method = RequestMethod.POST)
    public String updateSendPro(String orderId, String logisticsNumber, String logisticsCompany, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        orderInfoService
                .updateSendPro(Integer.parseInt(orderId), logisticsNumber, logisticsCompany, baseSessionUserDTO);
        return "redirect:/uc/orderInfo/getOrderList?orderState=2";
    }

    /**
     * 确认收货
     * 
     * @param orderId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateSurePro")
    @ResponseBody
    public String updateSurePro(String orderId, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        orderInfoService.updateSurePro(Integer.parseInt(orderId), baseSessionUserDTO);
        return "1";
    }

    /**
     * 取消订单
     * 
     * @param orderId
     * @param closeDesc
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateCancelPro")
    @ResponseBody
    public String updateCancelPro(String orderId, String closeDesc, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        orderInfoService.updateCancel(Integer.parseInt(orderId), closeDesc, baseSessionUserDTO);
        return "1";
    }

    @RequestMapping(value = "/changeOrderPrice")
    @ResponseBody
    public String changeOrderPrice(String orderId, String proAmount, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        orderInfoService.updateOrderPrice(Integer.parseInt(orderId), proAmount, baseSessionUserDTO);
        return "1";
    }

    /**
     * 查看物流
     * 
     * @param orderId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/viewLogistics")
    public String viewLogistics(String orderId, HttpSession session, HttpServletRequest request,
            HttpServletResponse respons, Model model) {
        OrderInfoPO orderInfoPO = orderInfoService.getByOrderId(Integer.valueOf(orderId));
        if (orderInfoPO != null) {
            LogiticsTrackDTO logiticsTrackDTO =
                    logisticsTrackService
                            .getLogisticInfo(orderInfoPO.getLogisticsComp(), orderInfoPO.getLogisticsNum());
            model.addAttribute("logiticsTrackList", logiticsTrackDTO.getData());
            model.addAttribute("logisticsNum", orderInfoPO.getLogisticsNum());
            model.addAttribute("logisticsCom", LogisticsCompanyConstants.getValue(orderInfoPO.getLogisticsComp())
                    .getValue());
        }
        return "/order/wuliuDetail";
    }
}
