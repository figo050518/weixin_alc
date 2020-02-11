package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FunctionListCriteria;
import com.fcgo.weixin.persist.po.FunctionListPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFunctionListMapper {
    int countByCriteria(FunctionListCriteria example);

    int deleteByCriteria(FunctionListCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FunctionListPO record);

    int insertSelective(FunctionListPO record);

    List<FunctionListPO> selectByCriteria(FunctionListCriteria example);

    FunctionListPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FunctionListPO record, @Param("example") FunctionListCriteria example);

    int updateByCriteria(@Param("record") FunctionListPO record, @Param("example") FunctionListCriteria example);

    int updateByPrimaryKeySelective(FunctionListPO record);

    int updateByPrimaryKey(FunctionListPO record);
}