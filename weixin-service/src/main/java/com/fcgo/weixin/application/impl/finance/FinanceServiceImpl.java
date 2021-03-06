package com.fcgo.weixin.application.impl.finance;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.finance.FinanceService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.generate.criteria.FinanceBankCardCriteria;
import com.fcgo.weixin.persist.generate.criteria.FinanceCapitalCriteria;
import com.fcgo.weixin.persist.po.FinanceBankCardPO;
import com.fcgo.weixin.persist.po.FinanceBillPO;
import com.fcgo.weixin.persist.po.FinanceCapitalPO;
import com.fcgo.weixin.persist.po.FinanceWithdrawApplyPO;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private IFinanceBankCardDAO financeBankCardDAO;
    @Autowired
    private IFinanceBillDAO financeBillDAO;

    @Autowired
    private IFinanceCapitalDAO financeCapitalDAO;

    @Autowired
    private IFinanceWithdrawApplyDAO financeWithdrawApplyDAO;

    @Override
    public BigDecimal getSellerbalance(int sellerId) {
        FinanceCapitalCriteria financeCapitalCriteria = new FinanceCapitalCriteria();
        financeCapitalCriteria.createCriteria().andSellerIdEqualTo(sellerId);
        List<FinanceCapitalPO> financeCapitalPOs = financeCapitalDAO.selectByCriteria(financeCapitalCriteria);
        if (financeCapitalPOs != null && financeCapitalPOs.size() > 0) {
            return financeCapitalPOs.get(0).getBalance();
        }
        return new BigDecimal(0);
    }

    @Override
    public BigDecimal getWithDrawApplyAmount(int sellerId) {
        BigDecimal withDrawApplyAmount = financeWithdrawApplyDAO.getWithDrawApplyAmount(sellerId);
        if (withDrawApplyAmount == null) {
            withDrawApplyAmount = new BigDecimal("0.00");
        }
        return withDrawApplyAmount;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<FinanceBillPO> getTranctaionList(int sellerId, Page page) {
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<FinanceBillPO> financeBillPOs = financeBillDAO.getTranctaionList(parm);
        for (FinanceBillPO financeBillPO : financeBillPOs) {
            financeBillPO.setAddTime(sd.format(financeBillPO.getCreateTime()));
        }
        return financeBillPOs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<FinanceBillPO> countTranctaionList(int sellerId) {
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        return financeBillDAO.countTranctaionList(parm);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<FinanceWithdrawApplyPO> getWithDrawApplyList(int sellerId, Page page) {
        Map parm = new HashMap();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        parm.put("sellerId", sellerId);
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        List<FinanceWithdrawApplyPO> financeWithdrawApplyPOs = financeWithdrawApplyDAO.getWithDrawApplyList(parm);
        for (FinanceWithdrawApplyPO financeWithdrawApplyPO : financeWithdrawApplyPOs) {
            financeWithdrawApplyPO.setAddTime(sd.format(financeWithdrawApplyPO.getCreateTime()));
        }
        return financeWithdrawApplyPOs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<FinanceWithdrawApplyPO> countWithDrawApplyList(int sellerId) {
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        return financeWithdrawApplyDAO.countWithDrawApplyList(parm);
    }

    @Override
    public boolean addCard(FinanceBankCardPO financeBankCardPO) {
        int i = financeBankCardDAO.insert(financeBankCardPO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<FinanceBankCardPO> getBankCardList(int sellerId) {
        FinanceBankCardCriteria financeBankCardCriteria = new FinanceBankCardCriteria();
        financeBankCardCriteria.createCriteria().andSellerIdEqualTo(sellerId).andIsDeleteEqualTo(0);
        List<FinanceBankCardPO> financeBankCardPOs = financeBankCardDAO.selectByCriteria(financeBankCardCriteria);
        for (FinanceBankCardPO financeBankCardPO : financeBankCardPOs) {
            financeBankCardPO.setCardNum(financeBankCardPO.getCardNum().substring(
                    financeBankCardPO.getCardNum().length() - 3));
        }
        return financeBankCardPOs;
    }

    @Override
    public void addWithDrawApply(FinanceWithdrawApplyPO financeWithdrawApplyPO) {
        financeWithdrawApplyDAO.insert(financeWithdrawApplyPO);
    }

    @Override
    public FinanceBankCardPO getFinanceBankCard(int cardId) {
        return financeBankCardDAO.selectByPrimaryKey(cardId);
    }

    @Override
    public boolean deleteCard(Integer cardId) {
        FinanceBankCardPO financeBankCardPO = new FinanceBankCardPO();
        financeBankCardPO.setId(cardId);
        financeBankCardPO.setIsDelete(1);
        financeBankCardPO.setUpdateTime(new Date());
        int i = financeBankCardDAO.updateByPrimaryKeySelective(financeBankCardPO);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
