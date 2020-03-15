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

    public static DadaApiResponse except() {
        DadaApiResponse apiResponse = new DadaApiResponse();
        apiResponse.setCode(STATUS_CODE);
        apiResponse.setMsg(STATUS_MSG);
        return apiResponse;
    }

    public boolean isOk(){
        String formatStatus;
        return Objects.nonNull(status)
                && (formatStatus=status.trim()).length() > 0
                && "success".equals(formatStatus);
    }
}
