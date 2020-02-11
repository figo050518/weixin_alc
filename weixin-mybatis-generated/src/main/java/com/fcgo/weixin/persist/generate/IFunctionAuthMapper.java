package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FunctionAuthCriteria;
import com.fcgo.weixin.persist.po.FunctionAuthPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFunctionAuthMapper {
    int countByCriteria(FunctionAuthCriteria example);

    int deleteByCriteria(FunctionAuthCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FunctionAuthPO record);

    int insertSelective(FunctionAuthPO record);

    List<FunctionAuthPO> selectByCriteria(FunctionAuthCriteria example);

    FunctionAuthPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FunctionAuthPO record, @Param("example") FunctionAuthCriteria example);

    int updateByCriteria(@Param("record") FunctionAuthPO record, @Param("example") FunctionAuthCriteria example);

    int updateByPrimaryKeySelective(FunctionAuthPO record);

    int updateByPrimaryKey(FunctionAuthPO record);
}