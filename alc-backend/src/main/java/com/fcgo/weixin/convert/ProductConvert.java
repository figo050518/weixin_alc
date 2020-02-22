package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.constant.PrdAuditStatus;
import com.fcgo.weixin.model.constant.PrdShelfStatus;
import com.fcgo.weixin.persist.model.Product;
import org.springframework.beans.BeanUtils;

public class ProductConvert {

    public static ProductBo do2Bo(Product product){

        ProductBo bo = ProductBo.builder().build();
        String[] ignoreProps = {"productSortId","createTime","updateTime"};
        BeanUtils.copyProperties(product, bo, ignoreProps);
        bo.setProductSortId(product.getProductSort());
        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(product.getCreateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        bo.setUpdateTime(DateUtil.getDateStrFromUnixTime(product.getUpdateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));

        return bo;
    }

    public static Product bo2Do4Insert(ProductBo bo){
        Product product = Product.builder().build();
        String[] ignoreProps = {"id","productSortId","status", "verifyStatus","createTime","updateTime"};
        BeanUtils.copyProperties(bo, product,  ignoreProps);
        product.setProductSort(bo.getProductSortId());
        int currentTime = DateUtil.getCurrentTimeSeconds();
        product.setCreateTime(currentTime);
        product.setUpdateTime(currentTime);
        product.setStatus(PrdShelfStatus.INIT.getCode());
        product.setVerifyStatus(PrdAuditStatus.INIT.getCode());
        return product;
    }

    public static Product bo2Do4Update(ProductBo bo){
        Product product = Product.builder().build();
        String[] ignoreProps = {"productSortId","status", "verifyStatus","createTime","updateTime"};
        BeanUtils.copyProperties(bo, product,  ignoreProps);
        product.setProductSort(bo.getProductSortId());
        int currentTime = DateUtil.getCurrentTimeSeconds();
        product.setUpdateTime(currentTime);
        product.setStatus(null);
        product.setVerifyStatus(null);
        return product;
    }
}
