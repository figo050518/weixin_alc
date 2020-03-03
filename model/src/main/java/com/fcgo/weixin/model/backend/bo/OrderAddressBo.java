package com.fcgo.weixin.model.backend.bo;

import lombok.Data;

@Data
public class OrderAddressBo {
    private Integer id;

    private Long orderCode;

    private String contactUser;

    private String phone;

    private String province;

    private String city;

    private String area;

    private String addressDetail;
}
