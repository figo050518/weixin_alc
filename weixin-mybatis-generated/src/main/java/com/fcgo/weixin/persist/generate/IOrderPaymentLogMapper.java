package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderPaymentLogCriteria;
import com.fcgo.weixin.persist.po.OrderPaymentLogPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderPaymentLogMapper {
    int countByCriteria(OrderPaymentLogCriteria example);

    int deleteByCriteria(OrderPaymentLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPaymentLogPO record);

    int insertSelective(OrderPaymentLogPO record);

    List<OrderPaymentLogPO> selectByCriteria(OrderPaymentLogCriteria example);

    OrderPaymentLogPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderPaymentLogPO record, @Param("example") OrderPaymentLogCriteria example);

    int updateByCriteria(@Param("record") OrderPaymentLogPO record, @Param("example") OrderPaymentLogCriteria example);

    int updateByPrimaryKeySelective(OrderPaymentLogPO record);

    int updateByPrimaryKey(OrderPaymentLogPO record);
}