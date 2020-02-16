package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
