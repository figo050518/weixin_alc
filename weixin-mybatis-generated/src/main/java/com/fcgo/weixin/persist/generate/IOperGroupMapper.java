package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OperGroupCriteria;
import com.fcgo.weixin.persist.po.OperGroupPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOperGroupMapper {
    int countByCriteria(OperGroupCriteria example);

    int deleteByCriteria(OperGroupCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperGroupPO record);

    int insertSelective(OperGroupPO record);

    List<OperGroupPO> selectByCriteria(OperGroupCriteria example);

    OperGroupPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OperGroupPO record, @Param("example") OperGroupCriteria example);

    int updateByCriteria(@Param("record") OperGroupPO record, @Param("example") OperGroupCriteria example);

    int updateByPrimaryKeySelective(OperGroupPO record);

    int updateByPrimaryKey(OperGroupPO record);
}