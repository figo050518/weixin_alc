package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.Order;
import com.fcgo.weixin.persist.model.dto.OrderListQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int selectCnt(@Param("dto")OrderListQueryDto dto);

    List<Order> selectAll(@Param("dto") OrderListQueryDto dto,
                          @Param("offset")int offset, @Param("limit")int limit);

    Order selectByOrderCode(Order record);

    int updateOrderStatusByOrderCode(Order record);
}