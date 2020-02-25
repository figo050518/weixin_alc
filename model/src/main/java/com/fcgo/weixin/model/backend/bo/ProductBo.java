package com.fcgo.weixin.model.backend.bo;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBo {

    private Integer id;

    private String name;

    private String unit;

    private Integer productSortId;

    private String productSortName;

    private ProductSortBo productSortBo;

    private String desc;

    private String picUrl;

    private Double price;

    private String priceUnit;

    private Integer brandId;

    private String brandName;

    private Integer status;

    private Integer verifyStatus;

    private String createTime;

    private String updateTime;
}
