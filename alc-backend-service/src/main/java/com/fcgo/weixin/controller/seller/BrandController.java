package com.fcgo.weixin.controller.seller;

import com.alibaba.fastjson.JSONObject;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.*;
import com.fcgo.weixin.model.backend.req.BrandListReq;
import com.fcgo.weixin.model.backend.req.WalletBillsListReq;
import com.fcgo.weixin.service.BrandAddressService;
import com.fcgo.weixin.service.BrandService;
import com.fcgo.weixin.service.BrandWalletBillsService;
import com.fcgo.weixin.service.ShopRechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandAddressService brandAddressService;

    @Autowired
    private ShopRechargeService shopRechargeService;

    @Autowired
    private BrandWalletBillsService brandWalletBillsService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ApiResponse getAll(){
        List<BrandBo> brandBos = brandService.getAll();
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(brandBos).build();
    }

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody BrandListReq req){
        logger.info("brand.getList req {}", req);
        PageResponseBO<BrandBo> pageResponseBO = brandService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/add")
    public ApiResponse add(@RequestBody BrandBo req){
        logger.info("/brand/add req {}", req);
        int rows = brandService.add(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("add brand successful")
                .build();
    }

    @RequestMapping("/update")
    public ApiResponse update(@RequestBody BrandBo req){
        logger.info("brand.update req {}", req);
        int rows = brandService.update(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("add brand successful")
                .build();
    }

    @RequestMapping("/addAddress")
    public ApiResponse addAddress(@RequestBody BrandAddressBo req){
        logger.info("brand.addAddress req {}", req);
        brandAddressService.add(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("add brand address successful")
                .build();
    }

    @RequestMapping("/updateAddress")
    public ApiResponse updateAddress(@RequestBody BrandAddressBo req){
        logger.info("brand.updateAddress req {}", req);
        brandAddressService.update(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("add brand address successful")
                .build();
    }

    @RequestMapping("/getRechargeUrl")
    public ApiResponse getRechargeUrl(@RequestBody RechargeUrlReq req) throws SessionExpireException {
        logger.info("brand.getRechargeUrl req {}", req);
        JSONObject result = shopRechargeService.getRechargeUrl(req);
        Integer code = 200;
        String msg = "getRechargeUrl successful";
        if (result==null){
            code = 400;
            msg = "getRechargeUrl fail";
        }
        return new ApiResponse.ApiResponseBuilder()
                .code(code)
                .data(result)
                .message(msg)
                .build();
    }

    @RequestMapping("/queryRechargeResult")
    public ApiResponse queryRechargeResult(RechargeOrderBo req) throws SessionExpireException {
        logger.info("queryRechargeResult in, req {}", req);
        RechargeOrderBo resp = shopRechargeService.queryRechargeOrder(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(resp)
                .message("success")
                .build();
    }


    @RequestMapping("/getWalletBillsList")
    public ApiResponse getWalletBillsList(@RequestBody WalletBillsListReq req) throws SessionExpireException {

        PageResponseBO<BrandWalletBillsBo> pageResponseBO = brandWalletBillsService.getList(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(pageResponseBO)
                .message("success")
                .build();
    }

    @RequestMapping("/getBrandWallet")
    public ApiResponse getBrandWallet() throws SessionExpireException {

        BrandWalletBo resp = brandWalletBillsService.getBrandWallet();
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(resp)
                .message("success")
                .build();
    }
}
