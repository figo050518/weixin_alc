package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSortBo {
    private Integer id;

    private String name;

    private Integer weight;

    private Byte status;

    private String createTime;

}
