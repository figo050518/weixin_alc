package com.fcgo.weixin.model.backend.bo;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBo {
    private Integer id;

    private String nickName;

    private String avatarUrl;

    private String phone;

    private String openId;

    private String province;

    private String city;

    private String status;

    private String createTime;
}
