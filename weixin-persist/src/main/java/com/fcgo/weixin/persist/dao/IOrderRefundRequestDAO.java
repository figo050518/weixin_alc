package com.fcgo.weixin.persist.dao;

import java.util.List;
import java.util.Map;

import com.fcgo.weixin.persist.dto.OrderRefundRequestUnionPO;
import com.fcgo.weixin.persist.generate.IOrderRefundRequestMapper;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;

public interface IOrderRefundRequestDAO extends IOrderRefundRequestMapper {
    /**
     * 分页查询售后订单列表
     * 
     * @param parm
     * @return
     */
    List<OrderRefundRequestPO> findOrdRefundByPage(Map parm);

    /**
     * 分页查询售后订单总数
     * 
     * @param parm
     * @return
     */
    int getCountBypage(Map parm);
    
    /**
     * 分页查询维权订单列表
     * @param param
     * @return
     */
    public List<OrderRefundRequestUnionPO> getOrdRefundRequestUnionListByPage(Map param);
    /**
     * 分页查询维权订单列表总数
     * @param param
     * @return
     */
    public int getOdrRfdRequestUnionCountByPage(Map param);
}
