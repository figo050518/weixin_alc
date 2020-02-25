package com.fcgo.weixin.model.backend.bo;

import lombok.*;

@Data
@ToString
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
