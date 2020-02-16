package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandBo {
    private Integer id;

    private String phone;

    private Integer weight;

    private String desc;

    private Short status;

    private String createTime;

    private String updateTime;
}
