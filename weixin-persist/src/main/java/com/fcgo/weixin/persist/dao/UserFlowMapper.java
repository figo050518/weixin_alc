package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.UserFlow;

public interface UserFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFlow record);

    int insertSelective(UserFlow record);

    UserFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFlow record);

    int updateByPrimaryKey(UserFlow record);
}