package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.RechargeOrder;

public interface RechargeOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeOrder record);

    int insertSelective(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeOrder record);

    int updateByPrimaryKey(RechargeOrder record);
}