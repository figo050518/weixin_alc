package com.fcgo.weixin.controller.order;

import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.model.backend.constant.OrderConstant;
import com.fcgo.weixin.dada.domain.order.OrderCallBackReq;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.service.LogisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogisticsService logisticsService;

    @RequestMapping("/" + OrderConstant.CALL_BACK_API)
    public ApiResponse dadaCallBack(@RequestBody OrderCallBackReq req){
        logger.info("in dadaCallBack, {}", req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("successful").build();
    }

    @RequestMapping("/queryDeliverFee")
    public ApiResponse queryDeliverFee(@RequestBody DeliverFeeReq req){
        logger.info("in queryDeliverFee, {}", req);

        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("successful").build();
    }

    @RequestMapping("/getCityCodeList")
    public ApiResponse getCityCodeList(){
        logger.info("in getCityCodeList ");
        Object list = logisticsService.getCityCodeList();
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(list)
                .message("successful").build();
    }
}
