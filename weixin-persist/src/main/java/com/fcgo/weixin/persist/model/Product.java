package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;

    private String name;

    private String unit;

    private Integer productSort;

    private String desc;

    private String picUrl;

    private Double price;

    private String priceUnit;

    private Integer brandId;

    private Integer status,exceptStatus;

    private Integer verifyStatus,exceptVerifyStatus;

    private Integer createTime;

    private Integer updateTime;


}