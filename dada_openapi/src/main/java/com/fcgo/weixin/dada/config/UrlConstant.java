package com.fcgo.weixin.dada.config;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public interface UrlConstant {

     String ORDER_ADD_URL = "/api/order/addOrder";

     String SHOP_ADD_URL = "/api/shop/add", SHOP_UPDATE = "/api/shop/update";



     String MERCHANT_ADD_URL = "/merchantApi/merchant/add";

     String CITY_CODE_URL = "/api/cityCode/list";

     String QUERY_DELIVER_FEE = "/api/order/queryDeliverFee", ADD_ORDER_AFTER_QUERY = "/api/order/addAfterQuery";
}
