package com.fcgo.weixin.persist.model;

import lombok.Data;

import java.util.Date;
@Data
public class Brand {
    private Integer id;

    private String name;

    private String phone;

    private String picUrl;

    private Date openTime;

    private Date closeTime;

    private Integer weight;

    private String desc;

    private Short status;

    private Integer createTime;

    private Integer updateTime;

    private Double deliveryThreshold;

}