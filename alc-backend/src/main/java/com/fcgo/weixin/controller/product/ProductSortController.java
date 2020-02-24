package com.fcgo.weixin.controller.product;

import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductSortBo;
import com.fcgo.weixin.model.backend.req.ProductSortListReq;
import com.fcgo.weixin.service.ProductSortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productSort")
public class ProductSortController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductSortService productSortService;

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody ProductSortListReq req){
        logger.info("/productSort/getList req {}", req);
        PageResponseBO<ProductSortBo> pageResponseBO = productSortService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/getAll")
    public ApiResponse getAll(){
        List<ProductSortBo> pageResponseBO = productSortService.getAll();
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/add")
    public ApiResponse save(@RequestBody ProductSortBo req){
        logger.info("/productSort/add req {}", req);
        productSortService.add(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .build();
    }

    @RequestMapping("/update")
    public ApiResponse update(@RequestBody ProductSortBo req){
        logger.info("/productSort/update req {}", req);
        productSortService.update(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .build();
    }
}
