package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.UserSessionInfoCriteria;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserSessionInfoMapper {
    int countByCriteria(UserSessionInfoCriteria example);

    int deleteByCriteria(UserSessionInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserSessionInfoPO record);

    int insertSelective(UserSessionInfoPO record);

    List<UserSessionInfoPO> selectByCriteria(UserSessionInfoCriteria example);

    UserSessionInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") UserSessionInfoPO record, @Param("example") UserSessionInfoCriteria example);

    int updateByCriteria(@Param("record") UserSessionInfoPO record, @Param("example") UserSessionInfoCriteria example);

    int updateByPrimaryKeySelective(UserSessionInfoPO record);

    int updateByPrimaryKey(UserSessionInfoPO record);
}