package com.fcgo.weixin.persist.model;

import lombok.Data;

@Data
public class OrderAddress {
    private Integer id;

    private String orderCode;

    private String contactUser;

    private String phone;

    private String province;

    private String city;

    private String area;

    private String addressDetail;


}