package com.fcgo.weixin.application.weixin;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;

public interface UserAutoLoginService {

    public BaseSessionUserDTO autoLogin(String openId, String nickName);

}
