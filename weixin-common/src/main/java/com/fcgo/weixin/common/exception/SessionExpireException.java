package com.fcgo.weixin.common.exception;

public class SessionExpireException extends  Exception {
    private int code;
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
