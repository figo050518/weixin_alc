package com.fcgo.weixin.controller.order;

import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.dada.domain.resp.BalanceResp;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.RechargeOrderBo;
import com.fcgo.weixin.model.backend.req.RechargeOrderListReq;
import com.fcgo.weixin.model.backend.resp.DadaRechargeUrlResp;
import com.fcgo.weixin.service.DadaRechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dada/recharge")
public class DadaRechargeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DadaRechargeService dadaRechargeService;


    @RequestMapping("/getUrl")
    public ApiResponse getUrl(@RequestBody RechargeUrlReq req) {
        logger.info("get dada recharge Url req {}", req);
        DadaRechargeUrlResp resp = dadaRechargeService.getDadaRechargeUrl(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(resp).build();
    }

    @RequestMapping("/getLeftBalance")
    public ApiResponse getLeftBalance(){
        BalanceResp resp = dadaRechargeService.getDadaLeftBalance();
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(resp).build();
    }


    @RequestMapping("/getShopRechargeOrderList")
    public ApiResponse getList(@RequestBody RechargeOrderListReq req) {
        logger.info("get shop RechargeOrder List req {}", req);
        PageResponseBO<RechargeOrderBo> pageResponseBO = dadaRechargeService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }
    @RequestMapping("/processDadaOrderOfBrand")
    public ApiResponse processDadaOrderOfBrand(@RequestBody RechargeOrderBo req) {
        logger.info("processDadaOrderOfBrand req {}", req);
        dadaRechargeService.processDadaOrderOfBrand(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .build();
    }


}