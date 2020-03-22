package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.dada.domain.merchant.ShopModel;
import com.fcgo.weixin.model.backend.bo.BrandAddressBo;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.BrandAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class BrandAddressConvert {

    public static BrandAddressBo do2Bo(BrandAddress brandAddress){
        BrandAddressBo bo = new BrandAddressBo();
        String[] ignoreProps = {"id","createTime","updateTime"};
        BeanUtils.copyProperties(brandAddress, bo,ignoreProps);
        return bo;
    }

    public static BrandAddress bo2Do4Insert(BrandAddressBo bo){
        BrandAddress brand = new BrandAddress();
        String[] ignoreProps = {"id","createTime","updateTime"};
        BeanUtils.copyProperties(bo,brand,ignoreProps);
        int currentDT = DateUtil.getCurrentTimeSeconds();
        brand.setCreateTime(currentDT);
        brand.setUpdateTime(currentDT);
        return brand;
    }

    public static BrandAddress bo2Do4Update(BrandAddressBo bo){
        BrandAddress brand = new BrandAddress();
        String[] ignoreProps = {"createTime","updateTime"};
        BeanUtils.copyProperties(bo,brand,ignoreProps);
        int currentDT = DateUtil.getCurrentTimeSeconds();
        brand.setUpdateTime(currentDT);
        return brand;
    }

    public static ShopModel convertToShopModel(BrandAddress brandAddress, Brand brand){
        ShopModel shopAddModel = ShopModel.builder()
                .originShopId(String.valueOf(brandAddress.getBrandId()))
                .cityName(brandAddress.getCityName())
                .areaName(brandAddress.getAreaName())
                .stationAddress(brandAddress.getStationAddress())
                .lat(BigDecimal.valueOf(Double.valueOf(brandAddress.getLat())))
                .lng(BigDecimal.valueOf(Double.valueOf(brandAddress.getLng())))
                .stationName(StringUtils.isBlank(brandAddress.getStationName()) ? brand.getName() : brand.getName())
                .business(2)
                .contactName(brandAddress.getContactName())
                .phone(brandAddress.getMobile())
                .build();
        return shopAddModel;
    }
}
