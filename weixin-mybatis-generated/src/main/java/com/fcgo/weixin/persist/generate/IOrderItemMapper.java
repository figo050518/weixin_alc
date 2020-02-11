package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderItemCriteria;
import com.fcgo.weixin.persist.po.OrderItemPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderItemMapper {
    int countByCriteria(OrderItemCriteria example);

    int deleteByCriteria(OrderItemCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderItemPO record);

    int insertSelective(OrderItemPO record);

    List<OrderItemPO> selectByCriteria(OrderItemCriteria example);

    OrderItemPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderItemPO record, @Param("example") OrderItemCriteria example);

    int updateByCriteria(@Param("record") OrderItemPO record, @Param("example") OrderItemCriteria example);

    int updateByPrimaryKeySelective(OrderItemPO record);

    int updateByPrimaryKey(OrderItemPO record);
}