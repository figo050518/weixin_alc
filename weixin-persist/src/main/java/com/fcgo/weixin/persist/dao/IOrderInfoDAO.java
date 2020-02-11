package com.fcgo.weixin.persist.dao;

import java.util.List;
import java.util.Map;

import com.fcgo.weixin.persist.generate.IOrderInfoMapper;
import com.fcgo.weixin.persist.po.OrderInfoPO;

public interface IOrderInfoDAO extends IOrderInfoMapper {
    // 订单分页信息
    @SuppressWarnings("rawtypes")
    List<OrderInfoPO> orderInfoPage(Map parm);

    // 获取订单总条数
    @SuppressWarnings("rawtypes")
    int getOrdInfoCount(Map parm);

    // 判断当前是否申请售后
    @SuppressWarnings("rawtypes")
    int getIsRefund(Map parm);
    
    //运营人员查看订单列表
    @SuppressWarnings("rawtypes")
    public List<OrderInfoPO> getOrderInfoListPage(Map parm);
}
