package com.fcgo.weixin.controller.product;

import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.backend.req.ProductAuditReq;
import com.fcgo.weixin.model.backend.req.ProductBatchReq;
import com.fcgo.weixin.model.backend.req.ProductCtrlShelveReq;
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
    public ApiResponse getList(@RequestBody ProductListReq req) throws SessionExpireException {
        logger.info("/product/getList req {}", req);
        PageResponseBO<ProductBo> pageResponseBO = productService.getList(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(pageResponseBO).build();
    }

    @RequestMapping("/add")
    public ApiResponse add(@RequestBody ProductBo req) throws SessionExpireException {
        logger.info("/product/add req {}", req);
        int result = productService.add(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(result).build();
    }

    @RequestMapping("/update")
    public ApiResponse update(@RequestBody ProductBo req) throws SessionExpireException {
        logger.info("/product/update req {}", req);
        int result = productService.update(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(result>0).build();
    }

    @RequestMapping("/audit")
    public ApiResponse audit(@RequestBody ProductAuditReq req) throws SessionExpireException {
        logger.info("/product/audit req {}", req);
        int result = productService.audit(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(result>0).build();
    }

    @RequestMapping("/auditBatch")
    public ApiResponse audit(@RequestBody ProductBatchReq req) throws SessionExpireException {
        logger.info("product auditBatch req {}", req);
        int result = productService.auditBatch(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(result>0).build();
    }

    @RequestMapping("/onOffShelve")
    public ApiResponse onOffShelve(@RequestBody ProductCtrlShelveReq req){
        logger.info("/product/onOffShelve req {}", req);
        int result = productService.onOffShelve(req);
        return new ApiResponse.ApiResponseBuilder().code(200).message("successful")
                .data(result>0).build();
    }
}
