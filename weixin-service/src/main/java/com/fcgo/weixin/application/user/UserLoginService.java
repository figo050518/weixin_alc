package com.fcgo.weixin.application.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.persist.po.UserLoginPO;

public interface UserLoginService {
    /**
     * 注册，插入用户登录信息表
     * 
     * @param userInfoPO
     * @return
     */
    public boolean addLoginInfo(UserLoginPO userLoginPO);

    /**
     * 判断用户名和密码是否正确
     * 
     * @param userLoginPO
     * @return
     */

    public boolean validateLogin(UserLoginPO userLoginPO);

    /**
     * 检查登录是否过期
     * 
     * @param signInTelephone
     * @param token
     * @param lastTimeAccessTime
     * @return
     */
    public boolean checkSignInPeriod(String signInTelephone, String token, String lastTimeAccessTime);

    /**
     * 根据token去查手机号，看是否存在
     * 
     * @param signInTelephone
     * @param token
     * @param lastTimeAccessTime
     * @return
     */

    public boolean validateSignInSuccToken(String signInTelephone, String token, String lastTimeAccessTime);

    public void initSignInFcg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            SignInFcgToken aizhuToken);

    public BaseSessionUserDTO getUserInfo(SignInFcgToken aizhuToken);

    /**
     * 忘记密码
     * 
     * @param telephone
     * @param password
     * @param validateCode
     * @return
     */

    public String forgotPassword(String telephone, String password, String validateCode);

}
