package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class User {
    private Integer id;

    private String nickName;

    private String avatarUrl;

    private String phone;

    private String openId;

    private String province;

    private String city;

    private String status;

    private Date createTime;

}