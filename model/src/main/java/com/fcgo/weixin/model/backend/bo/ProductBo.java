package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBo {

    private Integer id;

    private String name;

    private String unit;

    private Integer productSortId;

    private ProductSortBo productSortBo;

    private String desc;

    private String picUrl;

    private Double price;

    private String priceUnit;

    private Integer brandId;

    private Integer status;

    private Integer verifyStatus;

    private String createTime;

    private String updateTime;
}
