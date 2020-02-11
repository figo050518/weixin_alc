package com.fcgo.weixin.persist.dao;

import java.util.List;
import java.util.Map;

import com.fcgo.weixin.persist.po.SellerCustomerPO;

/**
 * 卖家客户关系
 * 
 * @author Ww
 */
public interface ISellerCustomerDAO {

    // 分页查询客户列表
    List<SellerCustomerPO> findCustomeLsitBySellerId(Map param);

    // 详细
    SellerCustomerPO findCustomeByDetail(Map param);

    // 分页总数量
    int findCustomeByCount(int sellerId);

    // 根据卖家ID 买家ID 订单状态 查询数量
    int findCountOrderState(Map param);
}
