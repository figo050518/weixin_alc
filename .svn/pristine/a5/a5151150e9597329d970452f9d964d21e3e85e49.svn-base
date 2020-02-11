package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FinanceWithdrawApplyCriteria;
import com.fcgo.weixin.persist.po.FinanceWithdrawApplyPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFinanceWithdrawApplyMapper {
    int countByCriteria(FinanceWithdrawApplyCriteria example);

    int deleteByCriteria(FinanceWithdrawApplyCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceWithdrawApplyPO record);

    int insertSelective(FinanceWithdrawApplyPO record);

    List<FinanceWithdrawApplyPO> selectByCriteria(FinanceWithdrawApplyCriteria example);

    FinanceWithdrawApplyPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FinanceWithdrawApplyPO record, @Param("example") FinanceWithdrawApplyCriteria example);

    int updateByCriteria(@Param("record") FinanceWithdrawApplyPO record, @Param("example") FinanceWithdrawApplyCriteria example);

    int updateByPrimaryKeySelective(FinanceWithdrawApplyPO record);

    int updateByPrimaryKey(FinanceWithdrawApplyPO record);
}