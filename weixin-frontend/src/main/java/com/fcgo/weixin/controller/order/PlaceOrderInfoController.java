package com.fcgo.weixin.controller.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcgo.weixin.application.dto.OrderConfirmDetailDTO;
import com.fcgo.weixin.application.dto.OrderConfirmProductDTO;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.application.order.PlaceOrderService;
import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.application.user.UserAddressService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.UserAddressPO;

/**
 * 下单
 * 
 * @author xiahanxzh
 */
@Controller
@RequestMapping("/uc/placeOrder")
public class PlaceOrderInfoController {
    @Autowired
    private PlaceOrderService placeOrderService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private SellerShopService sellerShopService;

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/confirmOrder")
    public String confirmOrder(Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response, String productInfoJson, @RequestHeader HttpHeaders headers) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        List<OrderConfirmDetailDTO> orderConfirmDetailDTOList = placeOrderService.resolveOrderInfoJson(productInfoJson);
        UserAddressPO userAddressPO = userAddressService.findDefaultByUserId(baseSessionUserDTO.getUserId());
        model.addAttribute("userAddressPO", userAddressPO);
        model.addAttribute("refer", headers.getFirst("Referer"));
        // 订单总价
        model.addAttribute("orderProductPrice", placeOrderService.getOrderTotalMoney(orderConfirmDetailDTOList));
        model.addAttribute("orderTotalFreight", placeOrderService.getOrderTotalFreight(orderConfirmDetailDTOList));
        for (OrderConfirmDetailDTO orderConfirmDetailDTO : orderConfirmDetailDTOList) {
            BigDecimal signleProductPrice = new BigDecimal(0);
            BigDecimal signleFreightPrice = new BigDecimal(0);
            for (OrderConfirmProductDTO orderConfirmProductDTO : orderConfirmDetailDTO.getOrderConfirmProductDTOs()) {
                ProductPO productPO = productService.getById(orderConfirmProductDTO.getProductId());
                if (productPO != null && productPO.getFromType() == 1) {
                    signleProductPrice =
                            signleProductPrice.add(orderConfirmProductDTO.getProductPrice().multiply(
                                    new BigDecimal(orderConfirmProductDTO.getProductCount())));
                    signleFreightPrice =
                            signleFreightPrice.add(productPO.getFreight().multiply(
                                    new BigDecimal(orderConfirmProductDTO.getProductCount())));
                }
                if (productPO != null && productPO.getFromType() == 2) {
                    signleProductPrice = signleProductPrice.add(null);
                    signleFreightPrice = signleFreightPrice.add(null);
                }

            }
            orderConfirmDetailDTO.setSingleTotalFreight(signleFreightPrice);
            orderConfirmDetailDTO.setSingleTotalProductPrice(signleProductPrice);
            orderConfirmDetailDTO.getOrderConfirmProductDTOs();
        }
        model.addAttribute("orderConfirmDetailDTOList", orderConfirmDetailDTOList);
        model.addAttribute(
                "orderTotalPrice",
                placeOrderService.getOrderTotalMoney(orderConfirmDetailDTOList).add(
                        placeOrderService.getOrderTotalFreight(orderConfirmDetailDTOList)));

        model.addAttribute("orderConfirmDetailDTOJson", JSONArray.fromObject(orderConfirmDetailDTOList));
        return "/order/confirmOrder";

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveOrder")
    public String saveOrder(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response,
            String productInfoJson, String addressId) {
        String remark[] = request.getParameterValues("remark");
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        UserAddressPO userAddressPO = userAddressService.findById(Integer.valueOf(addressId));
        JSONArray jsonArray = JSONArray.fromObject(productInfoJson);
        List<OrderConfirmDetailDTO> orderConfirmDetailDTOList = new ArrayList<OrderConfirmDetailDTO>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int sellerId = Integer.parseInt(jsonObject.get("sellerId").toString());
            String productInfo = jsonObject.get("orderConfirmProductDTOs").toString();
            JSONArray array = JSONArray.fromObject(productInfo);
            List<OrderConfirmProductDTO> productInfoList =
                    JSONArray.toList(array, new OrderConfirmProductDTO(), new JsonConfig());
            OrderConfirmDetailDTO orderConfirmDetailDTO = new OrderConfirmDetailDTO();
            orderConfirmDetailDTO.setSellerId(sellerId);
            orderConfirmDetailDTO.setOrderConfirmProductDTOs(productInfoList);
            if (StringUtils.isNotBlank(remark[i])) {
                orderConfirmDetailDTO.setRemark(remark[i]);
            }
            orderConfirmDetailDTOList.add(orderConfirmDetailDTO);

        }
        int parentOrderId = placeOrderService.placeOrder(orderConfirmDetailDTOList, baseSessionUserDTO, userAddressPO);
        return "redirect:/uc/placeOrder/immediatePaymentShow/" + parentOrderId;
    }

    /***
     * 立即付款
     * 
     * @param session
     * @param request
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/immediatePaymentShow/{parentOrderId}", method = RequestMethod.GET)
    public String immediatePaymentShow(HttpSession session, HttpServletRequest request, Model model,
            @PathVariable String parentOrderId) {
        model.addAttribute("actualPaymentMoney",
                orderInfoService.getAmountByParentOrderId(Integer.valueOf(parentOrderId)));
        model.addAttribute("orderId", parentOrderId);
        return "/order/orderPayment";
    }
}
