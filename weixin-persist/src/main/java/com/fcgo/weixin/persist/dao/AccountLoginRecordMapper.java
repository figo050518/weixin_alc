package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.AccountLoginRecord;

public interface AccountLoginRecordMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(AccountLoginRecord record);

    int insertSelective(AccountLoginRecord record);

    AccountLoginRecord selectByPrimaryKey(Integer uid);

    AccountLoginRecord selectBySessionKey(String sessionId);

    int updateByPrimaryKeySelective(AccountLoginRecord record);

    int updateByPrimaryKey(AccountLoginRecord record);
}