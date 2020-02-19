package com.fcgo.weixin.controller.user;

import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @RequestMapping("/add")
    public ApiResponse add(@RequestBody AccountBo req){
        logger.info("in Account.add req {}", req);
        accountService.add(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("add account successful")
                .build();

    }

    @RequestMapping("/update")
    public ApiResponse update(@RequestBody AccountBo req){
        logger.info("in Account.update req {}", req);
        accountService.update(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("update account successful")
                .build();

    }


    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody AccountListReq req){
        logger.info("in Account.list req {}", req);
        PageResponseBO pageResponseBO = accountService.getList(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(pageResponseBO)
                .message("get account list successful")
                .build();
    }
}
