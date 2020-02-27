package com.fcgo.weixin.controller.order;

import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.OrderBo;
import com.fcgo.weixin.model.backend.req.OrderDetailReq;
import com.fcgo.weixin.model.backend.req.OrderListReq;
import com.fcgo.weixin.model.backend.req.OrderProcessReq;
import com.fcgo.weixin.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody OrderListReq req) throws SessionExpireException {
        logger.info("order getList req {}", req);
        PageResponseBO<OrderBo> pageResponseBO = orderService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/getDetail")
    public ApiResponse getDetail(@RequestBody OrderDetailReq req) throws SessionExpireException {
        logger.info("order getDetail req {}", req);
        OrderBo pageResponseBO = orderService.getDetail(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/process")
    public ApiResponse process(@RequestBody OrderProcessReq req){
        logger.info("order process req {}", req);
        boolean result = orderService.process(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("successful")
                .data(result).build();
    }
}
