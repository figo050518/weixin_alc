package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OperLoginCriteria;
import com.fcgo.weixin.persist.po.OperLoginPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOperLoginMapper {
    int countByCriteria(OperLoginCriteria example);

    int deleteByCriteria(OperLoginCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperLoginPO record);

    int insertSelective(OperLoginPO record);

    List<OperLoginPO> selectByCriteria(OperLoginCriteria example);

    OperLoginPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OperLoginPO record, @Param("example") OperLoginCriteria example);

    int updateByCriteria(@Param("record") OperLoginPO record, @Param("example") OperLoginCriteria example);

    int updateByPrimaryKeySelective(OperLoginPO record);

    int updateByPrimaryKey(OperLoginPO record);
}