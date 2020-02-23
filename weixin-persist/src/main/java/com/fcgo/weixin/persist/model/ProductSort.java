package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSort {
    private Integer id;

    private String name;

    private Integer weight;

    private Byte status;

    private Integer createTime;


}