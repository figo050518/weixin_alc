package com.fcgo.weixin.model.backend.bo;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsBo {

    private Integer productTypeId;

    private String productType;

    private Integer productId;

    private String productName;

    private Double productPrice;

    private Integer productNumber;

    private String productUnit;
}
