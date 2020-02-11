package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FinanceCapitalCriteria;
import com.fcgo.weixin.persist.po.FinanceCapitalPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFinanceCapitalMapper {
    int countByCriteria(FinanceCapitalCriteria example);

    int deleteByCriteria(FinanceCapitalCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceCapitalPO record);

    int insertSelective(FinanceCapitalPO record);

    List<FinanceCapitalPO> selectByCriteria(FinanceCapitalCriteria example);

    FinanceCapitalPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FinanceCapitalPO record, @Param("example") FinanceCapitalCriteria example);

    int updateByCriteria(@Param("record") FinanceCapitalPO record, @Param("example") FinanceCapitalCriteria example);

    int updateByPrimaryKeySelective(FinanceCapitalPO record);

    int updateByPrimaryKey(FinanceCapitalPO record);
}