package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.RechargeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeOrderMapper {
    int selectCnt(@Param("condition")RechargeOrder record);

    List<RechargeOrder> selectAll(@Param("condition") RechargeOrder record,
                                  @Param("offset")int offset,
                                  @Param("limit")int limit);

    int insert(RechargeOrder record);

    int insertSelective(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer id);

    RechargeOrder selectByOrderCode(RechargeOrder record);

    int updateByPrimaryKeySelective(RechargeOrder record);

    int updateByPrimaryKey(RechargeOrder record);
}