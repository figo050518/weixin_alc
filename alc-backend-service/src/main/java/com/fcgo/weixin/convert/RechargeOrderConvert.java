package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.RechargeOrderBo;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.RechargeOrder;
import org.springframework.beans.BeanUtils;

public class RechargeOrderConvert {

    public static RechargeOrderBo do2Bo(RechargeOrder order, Brand brand){

        RechargeOrderBo bo = new RechargeOrderBo();
        String[] ignoreProps = {"createTime","updateTime"};
        BeanUtils.copyProperties(order, bo, ignoreProps);
        bo.setBrandName(brand.getName());
        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(order.getCreateTime(),DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        bo.setUpdateTime(DateUtil.getDateStrFromUnixTime(order.getUpdateTime(),DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }
}
