package com.fcgo.weixin.dada.config;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public interface UrlConstant {

     String ORDER_ADD_URL = "/api/order/addOrder";

     String ORDER_DETAIL = "/api/order/status/query";

     String SHOP_ADD_URL = "/api/shop/add", SHOP_UPDATE = "/api/shop/update";



     String MERCHANT_ADD_URL = "/merchantApi/merchant/add";

     String CITY_CODE_URL = "/api/cityCode/list";

     String QUERY_DELIVER_FEE = "/api/order/queryDeliverFee",
             ADD_ORDER_AFTER_QUERY = "/api/order/addAfterQuery";

     String CANCEL_REASON_LIST = "/api/order/cancel/reasons";

     String QUERY_RECHARGE_LINK = "/api/recharge",
     QUERY_BALANCE = "/api/balance/query";
}
