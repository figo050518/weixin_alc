package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderRefundRequestCriteria;
import com.fcgo.weixin.persist.po.OrderRefundRequestPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderRefundRequestMapper {
    int countByCriteria(OrderRefundRequestCriteria example);

    int deleteByCriteria(OrderRefundRequestCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRefundRequestPO record);

    int insertSelective(OrderRefundRequestPO record);

    List<OrderRefundRequestPO> selectByCriteria(OrderRefundRequestCriteria example);

    OrderRefundRequestPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderRefundRequestPO record, @Param("example") OrderRefundRequestCriteria example);

    int updateByCriteria(@Param("record") OrderRefundRequestPO record, @Param("example") OrderRefundRequestCriteria example);

    int updateByPrimaryKeySelective(OrderRefundRequestPO record);

    int updateByPrimaryKey(OrderRefundRequestPO record);
}