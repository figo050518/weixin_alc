package com.fcgo.weixin.application.finance;

import java.math.BigDecimal;
import java.util.List;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.FinanceBankCardPO;
import com.fcgo.weixin.persist.po.FinanceBillPO;
import com.fcgo.weixin.persist.po.FinanceWithdrawApplyPO;

public interface FinanceService {

    /**
     * 卖家总余额
     * 
     * @param sellerId
     * @return
     */
    public BigDecimal getSellerbalance(int sellerId);

    /**
     * 查询卖家提现的总额
     * 
     * @param sellerId
     * @return
     */
    public BigDecimal getWithDrawApplyAmount(int sellerId);

    /**
     * 查询卖家的流水明细
     * 
     * @param sellerId
     * @return
     */
    public List<FinanceBillPO> getTranctaionList(int sellerId, Page page);

    /**
     * 查询卖家的流水明细总数量
     * 
     * @param sellerId
     * @return
     */
    public List<FinanceBillPO> countTranctaionList(int sellerId);

    /**
     * 查询卖家的提现明细
     * 
     * @param sellerId
     * @return
     */
    public List<FinanceWithdrawApplyPO> getWithDrawApplyList(int sellerId, Page page);

    /**
     * 查询卖家的提现明细总数量
     * 
     * @param sellerId
     * @return
     */
    public List<FinanceWithdrawApplyPO> countWithDrawApplyList(int sellerId);

    /**
     * 绑定银行卡
     * 
     * @param financeBankCardPO
     * @return
     */

    public boolean addCard(FinanceBankCardPO financeBankCardPO);

    /**
     * 查询所有绑定的银行卡
     * 
     * @param sellerId
     * @return
     */
    public List<FinanceBankCardPO> getBankCardList(int sellerId);

    /**
     * 添加提现记录
     * 
     * @param financeWithdrawApplyPO
     */

    public void addWithDrawApply(FinanceWithdrawApplyPO financeWithdrawApplyPO);

    /**
     * 根据主键查询银行卡信息
     * 
     * @param cardId
     * @return
     */

    public FinanceBankCardPO getFinanceBankCard(int cardId);

    /**
     * 删除银行卡
     * 
     * @param valueOf
     * @return
     */

    public boolean deleteCard(Integer valueOf);

}
