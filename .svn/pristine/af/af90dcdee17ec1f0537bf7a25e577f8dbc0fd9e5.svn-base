package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.SellerShopCriteria;
import com.fcgo.weixin.persist.po.SellerShopPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ISellerShopMapper {
    int countByCriteria(SellerShopCriteria example);

    int deleteByCriteria(SellerShopCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SellerShopPO record);

    int insertSelective(SellerShopPO record);

    List<SellerShopPO> selectByCriteria(SellerShopCriteria example);

    SellerShopPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") SellerShopPO record, @Param("example") SellerShopCriteria example);

    int updateByCriteria(@Param("record") SellerShopPO record, @Param("example") SellerShopCriteria example);

    int updateByPrimaryKeySelective(SellerShopPO record);

    int updateByPrimaryKey(SellerShopPO record);
}