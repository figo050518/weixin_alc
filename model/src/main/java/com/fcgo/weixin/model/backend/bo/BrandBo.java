package com.fcgo.weixin.model.backend.bo;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandBo {
    private Integer id;

    private String name;

    private String phone;

    private String picUrl;

    private String openTime;

    private String closeTime;

    private Integer weight;

    private String desc;

    private Short status;

    private Double deliveryThreshold;

    private String createTime;

    private String updateTime;

    private BrandAddressBo brandAddress;
}
