package com.fcgo.weixin.controller.buyer;

import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.backend.req.UserListReq;
import com.fcgo.weixin.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userManage")
public class UserManageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserManageService userManageService;

    @RequestMapping("/getList")
    public ApiResponse getList(@RequestBody UserListReq req){
        logger.info("in Account.list req {}", req);
        PageResponseBO pageResponseBO = userManageService.getList(req);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(pageResponseBO)
                .message("get account list successful")
                .build();
    }
}
