package com.fcgo.weixin.controller.system.user;

import com.fcgo.weixin.common.annotation.IgnoreSession;
import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.model.ApiResponse;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.service.AccountService;
import com.fcgo.weixin.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @IgnoreSession
    public ApiResponse login(HttpServletRequest request,
                             @RequestBody AccountBo bo){
        logger.info("in account login req {}", bo);

        LoginUserResp resp = null;
        String msg = "登录失败";
        int code = 401;
        try {
            resp = loginService.login(request, bo);
            if (Objects.nonNull(resp)){
                code = 200;
                msg = "登录成功";
            }
        }catch (Exception ex){
            if (ex instanceof ServiceException){
                code = ((ServiceException) ex).getCode();
                msg = ((ServiceException) ex).getErrorMessage();
            }else{
                msg = "未知异常，请联系管理员";
                logger.warn("login occur unknown error,req {}", bo,ex);
            }
        }
        return new ApiResponse.ApiResponseBuilder()
                .code(code)
                .data(resp)
                .message(msg)
                .build();
    }

    @RequestMapping(value="/logout",method= RequestMethod.POST)
    public ApiResponse logout(HttpServletRequest request,
                             @RequestBody AccountBo bo) throws SessionExpireException {
        logger.info("in account logout req {}", bo);
        HttpSession session = request.getSession(false);
        String msg = "退出失败";
        int code = 401;
        boolean result = loginService.logout(session);
        if (result){
            code = 200;
            msg = "退出成功";
        }
        return new ApiResponse.ApiResponseBuilder()
                .code(code)
                .data(result)
                .message(msg)
                .build();
    }

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

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ApiResponse delete(@RequestParam("uid")Integer uid){
        int row = accountService.delete(uid);
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(row)
                .message("delete account  successful")
                .build();
    }

    @RequestMapping(value = "/getSessionFromCache")
    public ApiResponse getSessionFromCache(){
        Map<String,Map> result = new HashMap<>(2);
        result.put("sessionId", LoginService.getSidSessionCache());
        result.put("uid", LoginService.getUserIdSessionCache());
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .data(result)
                .message("get account list in cache successful")
                .build();
    }

    @RequestMapping(value = "/cleanSessionFromCache")
    public ApiResponse cleanSessionFromCache(){
        LoginService.getSidSessionCache().clear();
        LoginService.getUserIdSessionCache().clear();
        return new ApiResponse.ApiResponseBuilder()
                .code(200)
                .message("clean account list in cache successful")
                .build();
    }
}
