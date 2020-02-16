package com.fcgo.weixin.common.exception;


import lombok.Getter;

public class ServiceException extends RuntimeException {
    @Getter
    private final int code;
    @Getter
    private final String errorMessage;

    public ServiceException(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "["+code+":"+errorMessage+"]";
    }
}
