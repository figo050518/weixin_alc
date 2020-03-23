package com.fcgo.weixin.service;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.constant.BillsInOutType;
import com.fcgo.weixin.persist.dao.BrandWalletBillsMapper;
import com.fcgo.weixin.persist.dao.BrandWalletMapper;
import com.fcgo.weixin.persist.model.BrandWallet;
import com.fcgo.weixin.persist.model.BrandWalletBills;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BrandWalletMapper brandWalletMapper;

    @Autowired
    private BrandWalletBillsMapper brandWalletBillsMapper;


    public void substract(String orderCode,
                          Integer brandId,
                          BigDecimal amount,
                          BillsInOutType inOutType){
        int bizType = 1;
        int cdt = DateUtil.getCurrentTimeSeconds();
        BrandWallet bwc = BrandWallet.builder()
                .brandId(brandId)
                .amount(amount)
                .updateTime(cdt)
                .bizType(bizType)
                .build();
        int bwr = brandWalletMapper.updateNegative(bwc);
        logger.info("substract brand wallet condition {},cnt {}", bwc, bwr);
        if (bwr>0){
            BrandWalletBills bwbc = BrandWalletBills.builder()
                    .orderCode(orderCode)
                    .amount(amount)
                    .inOut(inOutType.getCode())
                    .bizType(bizType)
                    .createTime(cdt)
                    .brandId(brandId)
                    .build();
            addWalletBills(bwbc);
        }
    }

    public int addWalletBills(BrandWalletBills bwbc){

        return brandWalletBillsMapper.insert(bwbc);
    }

    public void plus(String orderCode,
                     Integer brandId,
                     BigDecimal amount, BillsInOutType inOutType){
        int bizType = 1;
        int cdt = DateUtil.getCurrentTimeSeconds();
        BrandWallet bwc = BrandWallet.builder()
                .brandId(brandId)
                .amount(amount)
                .updateTime(cdt)
                .bizType(bizType)
                .build();
        int bwr = brandWalletMapper.updatePositive(bwc);
        logger.info("plus brand wallet condition {},cnt {}", bwc, bwr);
        if (bwr>0){
            BrandWalletBills bwbc = BrandWalletBills.builder()
                    .orderCode(orderCode)
                    .amount(amount)
                    .inOut(inOutType.getCode())
                    .bizType(bizType)
                    .createTime(cdt)
                    .brandId(brandId)
                    .build();
            brandWalletBillsMapper.insert(bwbc);
        }
    }
}
