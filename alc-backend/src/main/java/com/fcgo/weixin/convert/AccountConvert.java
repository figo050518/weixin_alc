package com.fcgo.weixin.convert;

import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;

import java.util.Objects;

public class AccountConvert {

    public static AccountBo do2Bo(Account account, Brand brand){
        BrandBo brandBo = Objects.nonNull(brand) ? BrandBo.builder().id(brand.getId())
                .desc(brand.getDesc()).build() : null;
        AccountBo bo = AccountBo.builder()
                .id(account.getId())
                .pwd(account.getPwd())
                .name(account.getName())
                .brand(brandBo)
                .build();
        return bo;
    }

    public static Account bo2Do(AccountBo bo){
        Account account = Account.builder().id(bo.getId())
                .brandId(bo.getBrand().getId())
                .name(bo.getName())
                .pwd(bo.getPwd())
                .build();

        return account;
    }
}
