package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectCnt();

    List<User> selectAll(@Param("offset")int offset,
                         @Param("limit")int limit);

    List<User> selectByIds(@Param("ids")Collection<Integer> ids);
}