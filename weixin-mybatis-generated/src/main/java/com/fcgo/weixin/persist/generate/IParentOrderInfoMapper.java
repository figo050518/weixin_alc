package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ParentOrderInfoCriteria;
import com.fcgo.weixin.persist.po.ParentOrderInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IParentOrderInfoMapper {
    int countByCriteria(ParentOrderInfoCriteria example);

    int deleteByCriteria(ParentOrderInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParentOrderInfoPO record);

    int insertSelective(ParentOrderInfoPO record);

    List<ParentOrderInfoPO> selectByCriteria(ParentOrderInfoCriteria example);

    ParentOrderInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ParentOrderInfoPO record, @Param("example") ParentOrderInfoCriteria example);

    int updateByCriteria(@Param("record") ParentOrderInfoPO record, @Param("example") ParentOrderInfoCriteria example);

    int updateByPrimaryKeySelective(ParentOrderInfoPO record);

    int updateByPrimaryKey(ParentOrderInfoPO record);
}