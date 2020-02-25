package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.OrderBo;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.Order;
import org.springframework.beans.BeanUtils;

public final class OrderConvert {

    public static OrderBo do2Bo(Order order, Brand brand){

        OrderBo bo = new OrderBo();
        String[] ignoreProps = {"createTime","updateTime"};
        BeanUtils.copyProperties(order, bo, ignoreProps);
        bo.setBrandName(brand.getName());
        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(order.getCreateTime(),DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        bo.setUpdateTime(DateUtil.getDateStrFromUnixTime(order.getUpdateTime(),DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }
}
