package com.fcgo.weixin.model.backend.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandAddressBo {
    private Integer id;

    private Integer brandId;

    private String stationName;

    private String cityName;

    private String areaName;

    private String stationAddress;

    private String contactName;

    private String mobile;

    private String lng;

    private String lat;
}
