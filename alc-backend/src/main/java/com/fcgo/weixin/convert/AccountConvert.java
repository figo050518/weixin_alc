package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class AccountConvert {

    public static AccountBo do2Bo(Account account, Brand brand){
        BrandBo brandBo = Objects.nonNull(brand) ? BrandBo.builder()
                .id(brand.getId())
                .name(brand.getName())
                .desc(brand.getDesc()).build() : null;
        AccountBo bo = AccountBo.builder()
                .id(account.getId())
                .pwd(account.getPwd())
                .name(account.getName())
                .status(account.getStatus())
                .createTime(DateUtil.getDateStrFromUnixTime(account.getCreateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss))
                .updateTime(DateUtil.getDateStrFromUnixTime(account.getUpdateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss))
                .brand(brandBo)
                .build();

        return bo;
    }

    public static Account bo2Do(AccountBo bo){
        Account account = Account.builder().id(bo.getId())
                .brandId(bo.getBrand().getId())
                .name(bo.getName())
                .status(Objects.nonNull(bo.getStatus()) ? bo.getStatus() : null)
                .pwd(StringUtils.isNotBlank(bo.getPwd()) ? bo.getPwd().trim() : null)
                .build();

        return account;
    }
}
