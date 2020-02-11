package com.fcgo.weixin.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.user.ValidateCodeLogService;
import com.fcgo.weixin.controller.AbstractFrontController;

/**
 * 验证短信验证码
 * 
 * @author xiahanxzh
 */
@Controller
public class ValidateMsgController extends AbstractFrontController {

    @Autowired
    private ValidateCodeLogService validateCodeLogService;

    @Autowired
    private UserInfoService userRegisterService;

    /**
     * 注册时发送短信验证码
     * 
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sendMsgForRegister", method = RequestMethod.POST)
    public @ResponseBody
    String sendMsgForRegister(String telephone) {
        boolean result = userRegisterService.isExistTelephone(telephone);
        if (result) {
            return "existTelephone";
        }
        // 生成6位随机数验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String msgCode = "您注册菲常购微商城的手机验证码为：" + code + "，有效时间1分钟，为保障您的账号安全，请勿外泄。如有疑问请致电4001175858！【菲常购】";
        String msg =
                validateCodeLogService.insertMsgCodeForRegister(telephone, msgCode, Integer.valueOf(code).toString());
        return msg;
    }

    /**
     * 忘记密码时发送短信验证码
     * 
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sendMsgForgetPassword", method = RequestMethod.POST)
    public @ResponseBody
    String sendMsgForgetPassword(String telephone) {
        // 生成6位随机数验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String msgCode = "您正在重置密码,手机验证码为：" + code + "，有效时间1分钟，为保障您的账号安全，请勿外泄。如有疑问请致电4001175858！【菲常购】";
        String msg =
                validateCodeLogService.insertMsgCodeForRegister(telephone, msgCode, Integer.valueOf(code).toString());
        return msg;
    }

    /**
     * 绑定银行卡发送短信验证码
     * 
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sendMsgForBindCard", method = RequestMethod.POST)
    public @ResponseBody
    String sendMsgForBindCard(String telephone, String userId) {
        boolean result = userRegisterService.isExistTelephoneByUserId(telephone, userId);
        if (!result) {
            return "errorTelephone";
        }
        // 生成6位随机数验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String msgCode = "您绑定银行卡的手机验证码为：" + code + "，有效时间1分钟，为保障您的账号安全，请勿外泄。如有疑问请致电4001175858！【菲常购】";
        String msg =
                validateCodeLogService.insertMsgCodeForRegister(telephone, msgCode, Integer.valueOf(code).toString());
        return msg;
    }

    /**
     * 修改密码短信验证码
     * 
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sendMsgForChangePsd", method = RequestMethod.POST)
    public @ResponseBody
    String sendMsgForChangePsd(String telephone) {
        // 生成6位随机数验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String msgCode = "您修改密码的手机验证码为：" + code + "，有效时间1分钟，为保障您的账号安全，请勿外泄。如有疑问请致电4001175858！【菲常购】";
        String msg =
                validateCodeLogService.insertMsgCodeForRegister(telephone, msgCode, Integer.valueOf(code).toString());
        return msg;
    }

}
