package com.fcgo.weixin.controller.product;

import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.backend.req.BrandListReq;
import com.fcgo.weixin.model.backend.req.ProductListReq;
import com.fcgo.weixin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody ProductListReq req){
        logger.info("/product/getList req {}", req);
        PageResponseBO<ProductBo> pageResponseBO = productService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }
}
