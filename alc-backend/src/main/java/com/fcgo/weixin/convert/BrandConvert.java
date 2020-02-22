package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.model.constant.BrandStatus;
import com.fcgo.weixin.persist.model.Brand;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

public final class BrandConvert {

    public static BrandBo do2SimpleBo(Brand brand){
        BrandBo bo = new BrandBo();
        bo.setId(brand.getId());
        bo.setName(brand.getName());
        return bo;
    }

    public static BrandBo do2Bo(Brand brand){
        BrandBo bo = new BrandBo();
        String[] ignoreProps = {"openTime","closeTime","createTime","updateTime"};
        BeanUtils.copyProperties(brand, bo,ignoreProps);
        Date openTime,closeTime;
        if (Objects.nonNull(openTime=brand.getOpenTime())){
            bo.setOpenTime(DateUtil.format(openTime, DateUtil.Format_HH_mm_ss));
        }

        if (Objects.nonNull(closeTime=brand.getCloseTime())){
            bo.setCloseTime(DateUtil.format(closeTime, DateUtil.Format_HH_mm_ss));
        }

        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(brand.getCreateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        bo.setUpdateTime(DateUtil.getDateStrFromUnixTime(brand.getUpdateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }

    public static Brand bo2Do4Insert(BrandBo bo){
        Brand brand = new Brand();
        String[] ignoreProps = {"id","status","createTime","updateTime"};
        BeanUtils.copyProperties(bo,brand,ignoreProps);
        brand.setStatus(BrandStatus.USELESS.getCode());
        int currentDT = DateUtil.getCurrentTimeSeconds();
        brand.setCreateTime(currentDT);
        brand.setUpdateTime(currentDT);
        return brand;
    }

    public static Brand bo2Do4Update(BrandBo bo){
        Brand brand = new Brand();
        String[] ignoreProps = {"createTime","updateTime"};
        BeanUtils.copyProperties(bo,brand,ignoreProps);
        int currentDT = DateUtil.getCurrentTimeSeconds();
        brand.setUpdateTime(currentDT);
        return brand;
    }
}
