package com.fcgo.weixin.persist.model;

import lombok.Data;

@Data
public class BrandAddress {
    private Integer id;

    private Integer brandId;

    private String contactName;

    private String stationName;

    private String cityName;

    private String areaName;

    private String stationAddress;

    private String lng;

    private String lat;

    private Integer createTime;

    private Integer updateTime;

}