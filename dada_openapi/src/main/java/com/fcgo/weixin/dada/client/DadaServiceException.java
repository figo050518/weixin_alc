package com.fcgo.weixin.dada.client;

import lombok.Getter;

public class DadaServiceException extends RuntimeException {
    @Getter
    private final int code;
    @Getter
    private final String errorMessage;

    public DadaServiceException(int code, String errorMessage){
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "["+code+":"+errorMessage+"]";
    }
}
