package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ShoppingCartCriteria;
import com.fcgo.weixin.persist.po.ShoppingCartPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IShoppingCartMapper {
    int countByCriteria(ShoppingCartCriteria example);

    int deleteByCriteria(ShoppingCartCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCartPO record);

    int insertSelective(ShoppingCartPO record);

    List<ShoppingCartPO> selectByCriteria(ShoppingCartCriteria example);

    ShoppingCartPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ShoppingCartPO record, @Param("example") ShoppingCartCriteria example);

    int updateByCriteria(@Param("record") ShoppingCartPO record, @Param("example") ShoppingCartCriteria example);

    int updateByPrimaryKeySelective(ShoppingCartPO record);

    int updateByPrimaryKey(ShoppingCartPO record);
}