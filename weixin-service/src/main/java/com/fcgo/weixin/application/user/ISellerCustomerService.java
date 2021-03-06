package com.fcgo.weixin.application.user;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.SellerCustomerPO;

/**
 * 卖家客户关系
 * 
 * @author Ww
 */
public interface ISellerCustomerService {
    /**
     * 分页查询客户列表
     * 
     * @param sellerId
     * @param pageIndex
     * @return
     */
    Page findCustomeLsitBySellerId(int sellerId, int pageIndex);

    /**
     * 详细
     * 
     * @param sellerId
     * @param userId
     * @return
     */
    SellerCustomerPO findCustomeByDetail(int sellerId, int userId);

    /**
     * 查询不同订单总数
     * 
     * @param userId
     * @param sellerId
     * @param orderState
     * @return
     */
    int findCountOrderState(int userId, int sellerId, int orderState);
}
