package com.fcgo.weixin.common.exception;

import lombok.Getter;

public class SessionExpireException extends  Exception {
    @Getter
    private int code;
    @Getter
    private String desc;

    public SessionExpireException() {
        this(401, "登录会话超时,请退出重新登录.");
    }
    /**
     */
    public SessionExpireException(int code, String desc){
        this.code = code;
        this.desc=desc;

    }

    @Override
    public String getMessage() {
        return "[" + this.code + ":" + this.desc + "]";
    }
}
