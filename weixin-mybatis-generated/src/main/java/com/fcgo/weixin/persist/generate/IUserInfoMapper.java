package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.UserInfoCriteria;
import com.fcgo.weixin.persist.po.UserInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserInfoMapper {
    int countByCriteria(UserInfoCriteria example);

    int deleteByCriteria(UserInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoPO record);

    int insertSelective(UserInfoPO record);

    List<UserInfoPO> selectByCriteria(UserInfoCriteria example);

    UserInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") UserInfoPO record, @Param("example") UserInfoCriteria example);

    int updateByCriteria(@Param("record") UserInfoPO record, @Param("example") UserInfoCriteria example);

    int updateByPrimaryKeySelective(UserInfoPO record);

    int updateByPrimaryKey(UserInfoPO record);
}