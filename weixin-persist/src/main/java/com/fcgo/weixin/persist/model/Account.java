package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class Account {
    private Integer id;

    private String name;

    private String pwd;

    private Integer brandId;

    private Byte status;

    private Integer createTime;

    private Integer updateTime;

    private Byte isDel;
}