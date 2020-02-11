package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FinanceBillCriteria;
import com.fcgo.weixin.persist.po.FinanceBillPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFinanceBillMapper {
    int countByCriteria(FinanceBillCriteria example);

    int deleteByCriteria(FinanceBillCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceBillPO record);

    int insertSelective(FinanceBillPO record);

    List<FinanceBillPO> selectByCriteria(FinanceBillCriteria example);

    FinanceBillPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FinanceBillPO record, @Param("example") FinanceBillCriteria example);

    int updateByCriteria(@Param("record") FinanceBillPO record, @Param("example") FinanceBillCriteria example);

    int updateByPrimaryKeySelective(FinanceBillPO record);

    int updateByPrimaryKey(FinanceBillPO record);
}