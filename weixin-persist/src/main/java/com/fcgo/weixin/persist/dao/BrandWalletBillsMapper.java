package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandWalletBills;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandWalletBillsMapper {

    int insert(BrandWalletBills record);

    int insertSelective(BrandWalletBills record);

    BrandWalletBills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandWalletBills record);

    int updateByPrimaryKey(BrandWalletBills record);

    int selectCnt(@Param("condition")BrandWalletBills record);

    List<BrandWalletBills> selectAll(@Param("condition")BrandWalletBills record,
                                     @Param("offset")int offset,
                                     @Param("limit")int limit);

}