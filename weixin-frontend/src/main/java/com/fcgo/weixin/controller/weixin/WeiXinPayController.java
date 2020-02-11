package com.fcgo.weixin.controller.weixin;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.dto.OrderPayDTO;
import com.fcgo.weixin.application.dto.PayRequest;
import com.fcgo.weixin.application.order.IOrderInfoService;
import com.fcgo.weixin.application.order.IOrderReceiverAddressService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.weixin.WeixinPayService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.common.util.XmlUtil;
import com.fcgo.weixin.persist.dao.IOrderInfoDAO;
import com.fcgo.weixin.persist.dao.IParentOrderInfoDAO;
import com.fcgo.weixin.persist.generate.criteria.OrderInfoCriteria;
import com.fcgo.weixin.persist.generate.criteria.ParentOrderInfoCriteria;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import com.fcgo.weixin.persist.po.ParentOrderInfoPO;

@Controller
@RequestMapping("/weixin/pay")
public class WeiXinPayController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private IOrderInfoDAO orderInfoDAO;

    @Autowired
    private IParentOrderInfoDAO parentOrderInfoDAO;

    @Autowired
    private IOrderReceiverAddressService orderReceiverAddressService;

    @RequestMapping(value = "/getResponse", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getResponse(HttpSession session, HttpServletRequest request, String orderId) {
        OrderPayDTO orderPayDTO = orderInfoService.getOrderInfo4Pay(Integer.valueOf(orderId));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (true) {
            PayRequest payRequest = new PayRequest();
            payRequest.setTotal_fee(orderPayDTO.getTotalPrice().doubleValue());
            payRequest.setOut_trade_no(orderPayDTO.getOrderNumber());
            payRequest.setNotify_url("http://m.izhu100.com/weixin/pay/asyncWeiXinPayReturnInfo");
            payRequest.setSpbill_create_ip(getRemortIP(request));
            payRequest.setBody("菲常购微店订单");
            String openId = userInfoService.findById(baseSessionUserDTO.getUserId()).getWxId();
            Map<String, String> result = weixinPayService.jsApiPay(payRequest, openId);
            return result;
        }
        return null;

    }

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip)) {
            String ipArrey[] = ip.split(",");
            if (ipArrey.length > 1) {
                ip = ipArrey[0];
            }
        }
        return ip;
    }

    @RequestMapping(value = "/asyncWeiXinPayReturnInfo", method = RequestMethod.POST)
    public void asyncAcceptPayReturnInfo(Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        // 解析xml成map
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(result));
        Map<String, Object> resultMap = XmlUtil.Dom2Map(document);// 将返回的xml解析成map
        // 判断是否有这笔交易号了
        if (resultMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            String orderNumber = (String) resultMap.get("out_trade_no");
            boolean isPay = orderInfoService.validateIsPay(orderNumber);
            boolean flag = weixinPayService.hasTranactionLog(resultMap.get("transaction_id").toString());
            if (flag && isPay) {
                String resXml =
                        "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            }
            else {
                // 更新订单状态
                orderInfoService.updateOrderPayFinish(orderNumber, resultMap.get("transaction_id").toString());
                // 拆分订单
                String resXml =
                        "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            }
        }
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String updateName(HttpSession session, HttpServletRequest request, HttpServletResponse httpServletResponse,
            Model model) throws IOException {
        String orderId = request.getParameter("orderId");
        ParentOrderInfoCriteria parentOrderInfoCriteria = new ParentOrderInfoCriteria();
        parentOrderInfoCriteria.createCriteria().andIdEqualTo(Integer.valueOf(orderId));
        List<ParentOrderInfoPO> parentOrderInfoPOs = parentOrderInfoDAO.selectByCriteria(parentOrderInfoCriteria);
        OrderInfoCriteria orderInfoCriteria = new OrderInfoCriteria();
        orderInfoCriteria.createCriteria().andParentOrderIdEqualTo(parentOrderInfoPOs.get(0).getId());
        List<OrderInfoPO> orderInfoPOs = orderInfoDAO.selectByCriteria(orderInfoCriteria);
        model.addAttribute("orderReceiverAddressPO",
                orderReceiverAddressService.getByOrderId(orderInfoPOs.get(0).getId()));
        return "/order/paySuccess";
    }
}
