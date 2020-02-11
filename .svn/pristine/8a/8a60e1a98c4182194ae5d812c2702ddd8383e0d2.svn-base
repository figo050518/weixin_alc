package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.UserAddressCriteria;
import com.fcgo.weixin.persist.po.UserAddressPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserAddressMapper {
    int countByCriteria(UserAddressCriteria example);

    int deleteByCriteria(UserAddressCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAddressPO record);

    int insertSelective(UserAddressPO record);

    List<UserAddressPO> selectByCriteria(UserAddressCriteria example);

    UserAddressPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") UserAddressPO record, @Param("example") UserAddressCriteria example);

    int updateByCriteria(@Param("record") UserAddressPO record, @Param("example") UserAddressCriteria example);

    int updateByPrimaryKeySelective(UserAddressPO record);

    int updateByPrimaryKey(UserAddressPO record);
}