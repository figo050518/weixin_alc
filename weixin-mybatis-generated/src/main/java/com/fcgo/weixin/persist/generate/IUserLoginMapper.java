package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.UserLoginCriteria;
import com.fcgo.weixin.persist.po.UserLoginPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserLoginMapper {
    int countByCriteria(UserLoginCriteria example);

    int deleteByCriteria(UserLoginCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginPO record);

    int insertSelective(UserLoginPO record);

    List<UserLoginPO> selectByCriteria(UserLoginCriteria example);

    UserLoginPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") UserLoginPO record, @Param("example") UserLoginCriteria example);

    int updateByCriteria(@Param("record") UserLoginPO record, @Param("example") UserLoginCriteria example);

    int updateByPrimaryKeySelective(UserLoginPO record);

    int updateByPrimaryKey(UserLoginPO record);
}