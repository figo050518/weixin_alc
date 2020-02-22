package com.fcgo.weixin.controller.seller;

import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.model.backend.req.BrandListReq;
import com.fcgo.weixin.service.BrandService;
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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ApiResponse getAll(){
        List<BrandBo> brandBos = brandService.getAll();
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(brandBos).build();
    }

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody BrandListReq req){
        logger.info("/brand/getList req {}", req);
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
        logger.info("/brand/update req {}", req);
        int rows = brandService.update(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("add brand successful")
                .build();
    }
}
