package com.fcgo.weixin.application.user;

public interface ValidateCodeLogService {
    /**
     * 注册时候发送验证码
     * 
     * @param telephone
     */
    public String insertMsgCodeForRegister(String telephone, String message, String code);

}
