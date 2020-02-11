package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.UserProductFavCriteria;
import com.fcgo.weixin.persist.po.UserProductFavPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserProductFavMapper {
    int countByCriteria(UserProductFavCriteria example);

    int deleteByCriteria(UserProductFavCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserProductFavPO record);

    int insertSelective(UserProductFavPO record);

    List<UserProductFavPO> selectByCriteria(UserProductFavCriteria example);

    UserProductFavPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") UserProductFavPO record, @Param("example") UserProductFavCriteria example);

    int updateByCriteria(@Param("record") UserProductFavPO record, @Param("example") UserProductFavCriteria example);

    int updateByPrimaryKeySelective(UserProductFavPO record);

    int updateByPrimaryKey(UserProductFavPO record);
}