package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.FinanceBankCardCriteria;
import com.fcgo.weixin.persist.po.FinanceBankCardPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFinanceBankCardMapper {
    int countByCriteria(FinanceBankCardCriteria example);

    int deleteByCriteria(FinanceBankCardCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceBankCardPO record);

    int insertSelective(FinanceBankCardPO record);

    List<FinanceBankCardPO> selectByCriteria(FinanceBankCardCriteria example);

    FinanceBankCardPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") FinanceBankCardPO record, @Param("example") FinanceBankCardCriteria example);

    int updateByCriteria(@Param("record") FinanceBankCardPO record, @Param("example") FinanceBankCardCriteria example);

    int updateByPrimaryKeySelective(FinanceBankCardPO record);

    int updateByPrimaryKey(FinanceBankCardPO record);
}