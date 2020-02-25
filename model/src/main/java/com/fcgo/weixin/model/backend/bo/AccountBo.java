package com.fcgo.weixin.model.backend.bo;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor@AllArgsConstructor
public class AccountBo {

    private Integer id;

    private String name;

    private String pwd;

    private BrandBo brand;

    private Byte status;

    private String createTime;

    private String updateTime;
}
