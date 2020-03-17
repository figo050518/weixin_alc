package com.fcgo.weixin.dada.client;

import lombok.Data;

import java.util.Objects;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
@Data
public class DadaApiResponse<T> {

    private static final int STATUS_CODE = -2;

    private final static String STATUS_MSG = "请求超时异常";

    private String status;

    private int code;

    private String msg;

    private T result;

    private Integer errorCode;

    public static DadaServiceException isNullException(){
        return new DadaServiceException(500, "达达服务数据异常");
    }

    public DadaServiceException except() {
        DadaServiceException apiResponse = new DadaServiceException(this.code,this.msg);
        return apiResponse;
    }

    public boolean isOk(){
        String formatStatus;
        return Objects.nonNull(status)
                && (formatStatus=status.trim()).length() > 0
                && "success".equals(formatStatus);
    }
}
