package com.fcgo.weixin.dada.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class ApiResp<T> {

    String status;//		响应状态，成功为"success"，失败为"fail"
    Integer code;//		响应返回码，参考接口返回码
    String msg;//		响应描述
    T result;//响应结果，JSON对象，详见具体的接口描述
    Integer errorCode;//		错误编码，与code一致


}
