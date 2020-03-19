package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.BrandWalletBillsBo;
import com.fcgo.weixin.model.constant.BillsInOutType;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.BrandWalletBills;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class BrandWalletBillsConvert {

    public static BrandWalletBillsBo do2Bo(BrandWalletBills order, Brand brand){

        BrandWalletBillsBo bo = new BrandWalletBillsBo();
        String[] ignoreProps = {"createTime"};
        BeanUtils.copyProperties(order, bo, ignoreProps);
        bo.setBrandName(brand.getName());
        BillsInOutType billsInOutType = BillsInOutType.getDeliverType(order.getInOut());
        bo.setInOutDesc(Objects.isNull(billsInOutType) ? null : billsInOutType.getDeliverDesc());
        bo.setCreateTime(DateUtil.getDateStrFromUnixTime(order.getCreateTime(),DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }
}
