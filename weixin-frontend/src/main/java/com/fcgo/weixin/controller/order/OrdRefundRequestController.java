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

import com.fcgo.weixin.application.order.IOrdRefundRequestService;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.application.order.IOrderItemService;
import com.fcgo.weixin.application.order.IOrderReceiverAddressService;
import com.fcgo.weixin.application.order.IOrderRefundImageService;
import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.user.UserAddressService;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.OrderRefundRequestDTO;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.OrderItemPO;
import com.fcgo.weixin.persist.po.OrderReceiverAddressPO;
import com.fcgo.weixin.persist.po.OrderRefundImagePO;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.UserAddressPO;

/**
 * 售后订单
 * 
 * @author Ww
 */
@Controller
@RequestMapping("/uc/ordRefundRequest")
public class OrdRefundRequestController {
    @Autowired
    private IOrdRefundRequestService ordRefundRequestService;
    @Autowired
    private IOrderRefundImageService orderRefundImageService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderReceiverAddressService orderReceiverAddressService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private UserAddressService userAddressService;

    /**
     * 分页查询售后列表
     * 
     * @param model
     * @param refundStatus
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findOrdRufundBypage")
    public String findOrdRufundBypage(Model model, String refundStatus, String pageIndex, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // BaseSessionUserDTO baseSessionUserDTO = new BaseSessionUserDTO(null, null, true, false, 1, 1);
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        // 当售后状态为-1 时 查询所有售后订单
        if (StringUtils.isNotBlank(refundStatus) && refundStatus.equals("-1")) {
            refundStatus = null;
        }
        Page page =
                ordRefundRequestService.findOrdRufundBypage(baseSessionUserDTO, refundStatus,
                        Integer.parseInt(pageIndex));
        @SuppressWarnings("unchecked")
        List<OrderRefundRequestPO> orderRefundRequestPOs = (List<OrderRefundRequestPO>) page.getRows();
        // 组装返回结果
        List<OrderRefundRequestDTO> orderRefundRequestDTOs = new ArrayList<OrderRefundRequestDTO>();
        if (orderRefundRequestPOs != null && orderRefundRequestPOs.size() > 0) {
            for (OrderRefundRequestPO orderRefundRequestPO : orderRefundRequestPOs) {
                OrderRefundRequestDTO orderRefundRequestDTO = new OrderRefundRequestDTO();
                // 获取售后图片
                orderRefundRequestDTO.setOrderRefundImagePOs(orderRefundImageService
                        .getImagesByOrdRefundId(orderRefundRequestPO.getId()));
                orderRefundRequestDTO.setOrderRefundRequestPO(orderRefundRequestPO);
                // 获取商品来源（来源，1=自营，2=平台上架）
                ProductPO productPO = productService.getById(orderRefundRequestPO.getProductId());
                if (productPO != null && productPO.getFromType() != null) {
                    orderRefundRequestDTO.setProductType(productPO.getFromType());
                }
                // orderRefundRequestDTO.setProductType(11);
                orderRefundRequestDTOs.add(orderRefundRequestDTO);
            }
        }
        // 组装数据
        page.setRow(orderRefundRequestDTOs);
        model.addAttribute("page", page);
        if (StringUtils.isBlank(refundStatus)) {
            refundStatus = "-1";
        }
        model.addAttribute("refundStatus", refundStatus);
        // 返回用户身份
        if (baseSessionUserDTO.getIsBuyer()) {
            model.addAttribute("userType", UserType.BUYER.getKey());
        }
        else {
            model.addAttribute("userType", UserType.SELLER.getKey());
        }
        return "/order/afterSales";
    }

    /**
     * ajax请求分页数据
     * 
     * @param model
     * @param refundStatus
     * @param pageIndex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findOrdRufundBypageAjax")
    @ResponseBody
    public Page findOrdRufundBypageAjax(Model model, String refundStatus, String pageIndex, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 判断当前页码是否为空，为空则默认1
        if (StringUtils.isBlank(pageIndex)) {
            pageIndex = "1";
        }
        // 当售后状态为-1 时 查询所有售后订单
        if (StringUtils.isNotBlank(refundStatus) && refundStatus.equals("-1")) {
            refundStatus = null;
        }
        Page page =
                ordRefundRequestService.findOrdRufundBypage(baseSessionUserDTO, refundStatus,
                        Integer.parseInt(pageIndex));
        @SuppressWarnings("unchecked")
        List<OrderRefundRequestPO> orderRefundRequestPOs = (List<OrderRefundRequestPO>) page.getRows();
        // 组装返回结果
        List<OrderRefundRequestDTO> orderRefundRequestDTOs = new ArrayList<OrderRefundRequestDTO>();
        if (orderRefundRequestPOs != null && orderRefundRequestPOs.size() > 0) {
            for (OrderRefundRequestPO orderRefundRequestPO : orderRefundRequestPOs) {
                OrderRefundRequestDTO orderRefundRequestDTO = new OrderRefundRequestDTO();
                // 获取售后图片
                orderRefundRequestDTO.setOrderRefundImagePOs(orderRefundImageService
                        .getImagesByOrdRefundId(orderRefundRequestPO.getId()));
                orderRefundRequestDTO.setOrderRefundRequestPO(orderRefundRequestPO);
                // 获取商品来源（来源，1=自营，2=平台上架）
                orderRefundRequestDTO.setProductType(productService.getById(orderRefundRequestPO.getProductId())
                        .getFromType());
                orderRefundRequestDTOs.add(orderRefundRequestDTO);
            }
        }
        // 组装数据
        page.setRow(orderRefundRequestDTOs);
        return page;
    }

    /**
     * 售后详细
     * 
     * @param model
     * @param ordRefundId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "findBydetail")
    public String findBydetail(Model model, String ordRefundId, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {

        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 售后主表
        OrderRefundRequestPO orderRefundRequestPO = ordRefundRequestService.findById(Integer.parseInt(ordRefundId));
        model.addAttribute("orderRefundRequestPO", orderRefundRequestPO);
        // 售后图片信息
        List<OrderRefundImagePO> orderRefundImagePOs =
                orderRefundImageService.getImagesByOrdRefundId(Integer.parseInt(ordRefundId));
        model.addAttribute("orderRefundImagePOs", orderRefundImagePOs);
        // 查询商品来源// 获取商品来源（来源，1=自营，2=平台上架）
        ProductPO productPO = productService.getById(orderRefundRequestPO.getProductId());
        model.addAttribute("productFromType", productPO.getFromType());
        // 返回用户身份
        if (baseSessionUserDTO.getIsBuyer()) {
            model.addAttribute("userType", UserType.BUYER.getKey());
        }
        else {
            model.addAttribute("userType", UserType.SELLER.getKey());
        }
        // 收货人地址信息
        OrderReceiverAddressPO orderReceiverAddressPO =
                orderReceiverAddressService.getByOrderId(orderRefundRequestPO.getOrderId());
        // 卖家地址信息
        UserAddressPO sellerAddress = userAddressService.findById(orderRefundRequestPO.getSellerId());
        model.addAttribute("sellerAddress", sellerAddress);
        model.addAttribute("orderReceiverAddressPO", orderReceiverAddressPO);
        return "/order/afterSalesDetails";

    }

    /**
     * fcg订单移交与fcg
     * 
     * @param model
     * @param ordRefundId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateTransferFcg")
    @ResponseBody
    public String updateTransferFcg(Model model, String ordRefundId, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ordRefundRequestService.updateTransferFcg(Integer.parseInt(ordRefundId), baseSessionUserDTO);
        return "1";
    }

    /**
     * 卖家售后审核
     * 
     * @param model
     * @param ordRefundId
     * @param auditState
     * @param redundsReason
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateAuditBySeller")
    @ResponseBody
    public String updateAuditBySeller(Model model, String ordRefundId, String refundStatus, String refuseReason,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ordRefundRequestService.updateAuditBySeller(Integer.parseInt(ordRefundId), refundStatus, refuseReason,
                baseSessionUserDTO);
        return "1";
    }

    /**
     * 完成售后
     * 
     * @param model
     * @param ordRefundId
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateFinsh")
    @ResponseBody
    public String updateFinsh(Model model, String ordRefundId, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ordRefundRequestService.updateFinsh(Integer.parseInt(ordRefundId), baseSessionUserDTO);
        return "1";
    }

    /**
     * 申请售后
     * 
     * @param model
     * @param orderRefundRequestPO
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insertInfo")
    @ResponseBody
    public String insertInfo(Model model, OrderRefundRequestPO orderRefundRequestPO, HttpSession session,
            HttpServletRequest request, HttpServletResponse response, String itemId) {
        // session 获取用户登录信息
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ordRefundRequestService.insertInfo(orderRefundRequestPO, baseSessionUserDTO, itemId);
        return "1";
    }

    /**
     * 申请售后初始化
     * 
     * @param orderId
     * @param ordItemId
     * @return
     */
    @RequestMapping(value = "/getInsetInfo")
    public String getInsetInfo(String orderId, String ordItemId, Model model) {
        // 返回订单信息
        OrderInfoPO orderInfoPO = orderInfoService.getByOrderId(Integer.parseInt(orderId));
        model.addAttribute("orderInfoPO", orderInfoPO);
        // 返回订单项信息
        OrderItemPO orderItemPO = orderItemService.getById(Integer.parseInt(ordItemId));
        model.addAttribute("orderItemPO", orderItemPO);
        return "/order/acceptAfterSales";

    }

    /**
     * 买家确认退回货物已发送
     * 
     * @param ordRefundId
     * @param logisticsCompany
     * @param logisticsNumber
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateTuihuiPro", method = RequestMethod.POST)
    public String updateTuihuiPro(String logisticsOrdRefundId, String logisticsCompany, String logisticsNumber,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        ordRefundRequestService.updateTuihuiPro(Integer.parseInt(logisticsOrdRefundId), logisticsCompany,
                logisticsNumber, baseSessionUserDTO);
        return "redirect:/uc/ordRefundRequest/findOrdRufundBypage";

    }
}
