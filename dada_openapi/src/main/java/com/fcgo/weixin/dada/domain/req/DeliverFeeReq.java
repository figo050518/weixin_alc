package com.fcgo.weixin.dada.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliverFeeReq {
    private String shop_no;
    private String origin_id;
    private String city_code;
    private String cargo_price;
    /**
     * 是否需要垫付 1:是 0:否 (垫付订单金额，非运费)
     */
    private Integer is_prepay;
    private String receiver_name;
    private String receiver_address;
    private String receiver_lat;
    private String receiver_lng;
    private String receiver_phone;
    private String callback;
}
