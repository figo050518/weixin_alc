package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectAllByCondtion(@Param("condition") Account condition,
                                      @Param("offset") int offset,
                                      @Param("limit")int limit
    );

    int selectCntByCondition(Account condition);

    Account selectByPrimaryKey(Integer id);

    Account selectByName(String name);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

}