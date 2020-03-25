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
                .lat(cutSpecialLen(brandAddress.getLat(),6))
                .lng(cutSpecialLen(brandAddress.getLng(),6))
                .stationName(StringUtils.isBlank(brandAddress.getStationName()) ? brand.getName() : brand.getName())
                .business(2)
                .contactName(brandAddress.getContactName())
                .phone(brandAddress.getMobile())
                .build();
        return shopAddModel;
    }

    static BigDecimal cutSpecialLen(String numStr,int len){
        return new BigDecimal(numStr).setScale(len,BigDecimal.ROUND_FLOOR);
    }

    public static void main(String[] args) {
        BigDecimal result = new BigDecimal("118.78649898658985").setScale(6,BigDecimal.ROUND_FLOOR);
        System.out.println(result);
    }
}
