package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.BigDecimalHelper;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.persist.model.Order;
import com.fcgo.weixin.persist.model.OrderAddress;

public class LogisticsOrderConvert {

    public static final DeliverFeeReq buildDeliverFeeReq(Order order,
                                                         String callBackUrl,
                                                         OrderAddress orderAddress){
        String orderCode = order.getCode();
        Integer brandId = order.getBrandId();
        DeliverFeeReq dfr = DeliverFeeReq.builder()
                .callback(callBackUrl)
                .shop_no(String.valueOf(brandId))
                .origin_id(orderCode)
                //TODO get city code from buyer order address
                .city_code("025")
                .cargo_price(BigDecimalHelper.format(order.getAmount()))
                .is_prepay(0)
                .receiver_name(orderAddress.getContactUser())
                .receiver_address(orderAddress.getAddressDetail())
                .receiver_phone(orderAddress.getPhone())
                .build()
                ;
        return dfr;
    }
}
