package com.fcgo.weixin.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.application.order.IOrderItemService;
import com.fcgo.weixin.application.order.IOrderReceiverAddressService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.constants.OrderStateConstants;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.dto.OrderItemsDTO;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.OrderItemPO;
import com.fcgo.weixin.persist.po.OrderReceiverAddressPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.uitl.PaginationContext;

/**
 * 订单操作
 * 
 * @author guangyang
 */
@Controller
@RequestMapping("/order")
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
    private IProductImageService productImageService;

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
    @RequestMapping("/orderList")
    public String orderList(Model model, HttpServletRequest request, HttpServletResponse response,
            OrderInfoPO orderInfoPO) {
        String pageIndex = request.getParameter("pageIndex");

        if (StringUtils.isEmpty(pageIndex)) {
            pageIndex = "1";
        }

        Page page = orderInfoService.getOrderInfoListPage(orderInfoPO, Integer.valueOf(pageIndex));
        model.addAttribute("seachInfo", orderInfoPO);
        model.addAttribute("paginationContext", new PaginationContext(page));
        model.addAttribute("orderStates", OrderStateConstants.values());
        return "/order/orders";
    }

    /**
     * 根据订单ID查询订单详细
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/orderDetail")
    public String orderDetail(Model model, HttpServletRequest request, HttpServletResponse response) {

        // 订单id
        String orderId = request.getParameter("id");
        if (StringUtils.isEmpty(orderId)) {
            return "/order/order";
        }
        // 订单主表信息
        OrderInfoPO orderInfoPO = orderInfoService.getByOrderId(Integer.valueOf(orderId));
        model.addAttribute("orderInfoPO", orderInfoPO);

        model.addAttribute("orderStates", OrderStateConstants.values());
        // 子表信息
        List<OrderItemPO> orderItemPOs = orderItemService.getByOrderId(Integer.valueOf(orderId));
        List<OrderItemsDTO> orderItemsDTOs = new ArrayList<OrderItemsDTO>();
        for (OrderItemPO orderItemPO : orderItemPOs) {
            OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
            orderItemsDTO.setOrderItemPO(orderItemPO);
            orderItemsDTO.setImageUrl(orderItemPO.getProductPicUrl());
            orderItemsDTOs.add(orderItemsDTO);
        }
        model.addAttribute("orderItemsDTOs", orderItemsDTOs);

        // 收货地址信息
        OrderReceiverAddressPO orderReceiverAddressPO =
                orderReceiverAddressService.getByOrderId(Integer.valueOf(orderId));
        model.addAttribute("orderReceiverAddressPO", orderReceiverAddressPO);

        // 下单用户基本信息
        UserInfoPO buyerInfo = userInfoService.findById(orderInfoPO.getUserId());
        model.addAttribute("buyerInfo", buyerInfo);

        // 卖家基本信息
        UserInfoPO sellerInfo = userInfoService.findById(orderInfoPO.getUserId());
        model.addAttribute("sellerInfo", sellerInfo);

        return "/order/order";
    }

}
