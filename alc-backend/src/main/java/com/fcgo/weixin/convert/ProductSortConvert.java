package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.ProductSortBo;
import com.fcgo.weixin.persist.model.ProductSort;
import org.springframework.beans.BeanUtils;

public final class ProductSortConvert {

    public static ProductSortBo do2Bo(ProductSort productSort){
        ProductSortBo bo = new ProductSortBo();
        String[] ignoreProps = {"createTime"};
        BeanUtils.copyProperties(productSort,bo, ignoreProps);
        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(productSort.getCreateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }

    public static ProductSort bo2Do4Insert(ProductSortBo bo){
        ProductSort productSort = new ProductSort();
        String[] ignoreProps = {"id","createTime"};
        BeanUtils.copyProperties(bo, productSort, ignoreProps);
        int currentTime = DateUtil.getCurrentTimeSeconds();
        productSort.setCreateTime(currentTime);
        return productSort;
    }

    public static ProductSort bo2Do4Update(ProductSortBo bo){
        ProductSort productSort = new ProductSort();
        String[] ignoreProps = {"createTime"};
        BeanUtils.copyProperties(bo, productSort, ignoreProps);
        return productSort;
    }
}
