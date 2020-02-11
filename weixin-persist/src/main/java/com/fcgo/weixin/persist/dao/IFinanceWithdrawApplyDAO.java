package com.fcgo.weixin.persist.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fcgo.weixin.persist.generate.IFinanceWithdrawApplyMapper;
import com.fcgo.weixin.persist.po.FinanceWithdrawApplyPO;

public interface IFinanceWithdrawApplyDAO extends IFinanceWithdrawApplyMapper {

    public BigDecimal getWithDrawApplyAmount(@Param("sellerId") int sellerId);

    public List<FinanceWithdrawApplyPO> getWithDrawApplyList(Map map);

    public List<FinanceWithdrawApplyPO> countWithDrawApplyList(Map map);
}
