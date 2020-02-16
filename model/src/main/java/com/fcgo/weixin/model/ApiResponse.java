package com.fcgo.weixin.model;


import lombok.Data;

import java.util.Objects;

/**
 * API 响应格式
 * /**
 * 响应为：
 * <pre>
 * {
 * "code": 200,
 * "message": "\u767b\u5f55\u6210\u529f",
 * “alg": "SALT_MD5"
 * "data": {
 * "uid": "10216497",
 * "passport": "18751986615",
 * "session_key": "fa31d3a5d069c6c98cd8c38c3a5f89e6",
 * "vip": 0
 * },
 * "md5": "fa5b07f95a0bf95c26ac50abf0024eed"
 * }
 * Created by chang@yoho.cn on 2015/11/3.
 */
@Data
public class ApiResponse {

    private static String DEFAULT_MSG = "操作成功";
    private static int DEFAULT_CODE = 200;
    private static  final  String MD5_SALT = "fd4ad5fcsa0de589af23234ks1923ks";

    private int code;
    private String message;
    private String md5;

    //如果客户端判断有这个，则校验MD5
    private String alg = "SALT_MD5";
    private Object data;

    public ApiResponse() {
        this(200, DEFAULT_MSG, null);
    }


    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        if (Objects.nonNull(message)) {
            this.message = message;
        }
        this.data = data;
    }








    /**
     * 构造响应。 使用方式：
     * <p/>
     * <pre>
     *  ApiResponse.ApiResponseBuilder builder = new ApiResponse.ApiResponseBuilder();
     *  ApiResponse apiResponse =  builder.code(200).message("coupons total").data(new Total("0")).build();
     * </pre>
     */
    public static class ApiResponseBuilder {
        ApiResponse apiResponse;

        public ApiResponseBuilder() {
            apiResponse = new ApiResponse();
        }

        /**
         * 设置错误码。默认200
         *
         * @param code 错误码
         * @return ApiResponseBuilder
         */
        public ApiResponseBuilder code(int code) {
            apiResponse.code = code;
            return this;
        }

        /**
         * 设置消息。默认[操作成功]
         *
         * @param message 错误消息
         * @return ApiResponseBuilder
         */
        public ApiResponseBuilder message(String message) {
            apiResponse.message = message;
            return this;
        }



        /**
         * 设置响应的具体内容
         *
         * @param data 响应的具体内容
         * @return 内容
         */
        public ApiResponseBuilder data(Object data) {
            apiResponse.data = data;
            return this;
        }

        /**
         * 构造响应
         *
         * @return 响应
         */
        public ApiResponse build() {
            //参数校验, 并且设置默认值
            if (this.apiResponse.code <= 0) {
                this.apiResponse.code = DEFAULT_CODE;
            }
            if (Objects.nonNull(apiResponse.message)) {
                this.apiResponse.message = DEFAULT_MSG;
            }

            return apiResponse;
        }



    }


}
