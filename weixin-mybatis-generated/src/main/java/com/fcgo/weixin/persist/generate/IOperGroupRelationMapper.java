package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OperGroupRelationCriteria;
import com.fcgo.weixin.persist.po.OperGroupRelationPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOperGroupRelationMapper {
    int countByCriteria(OperGroupRelationCriteria example);

    int deleteByCriteria(OperGroupRelationCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperGroupRelationPO record);

    int insertSelective(OperGroupRelationPO record);

    List<OperGroupRelationPO> selectByCriteria(OperGroupRelationCriteria example);

    OperGroupRelationPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OperGroupRelationPO record, @Param("example") OperGroupRelationCriteria example);

    int updateByCriteria(@Param("record") OperGroupRelationPO record, @Param("example") OperGroupRelationCriteria example);

    int updateByPrimaryKeySelective(OperGroupRelationPO record);

    int updateByPrimaryKey(OperGroupRelationPO record);
}