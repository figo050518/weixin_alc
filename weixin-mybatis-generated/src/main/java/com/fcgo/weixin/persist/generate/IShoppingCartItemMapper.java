package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ShoppingCartItemCriteria;
import com.fcgo.weixin.persist.po.ShoppingCartItemPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IShoppingCartItemMapper {
    int countByCriteria(ShoppingCartItemCriteria example);

    int deleteByCriteria(ShoppingCartItemCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCartItemPO record);

    int insertSelective(ShoppingCartItemPO record);

    List<ShoppingCartItemPO> selectByCriteria(ShoppingCartItemCriteria example);

    ShoppingCartItemPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ShoppingCartItemPO record, @Param("example") ShoppingCartItemCriteria example);

    int updateByCriteria(@Param("record") ShoppingCartItemPO record, @Param("example") ShoppingCartItemCriteria example);

    int updateByPrimaryKeySelective(ShoppingCartItemPO record);

    int updateByPrimaryKey(ShoppingCartItemPO record);
}