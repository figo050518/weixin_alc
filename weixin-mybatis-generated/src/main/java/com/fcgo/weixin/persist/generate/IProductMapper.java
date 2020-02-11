package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ProductCriteria;
import com.fcgo.weixin.persist.po.ProductPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IProductMapper {
    int countByCriteria(ProductCriteria example);

    int deleteByCriteria(ProductCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPO record);

    int insertSelective(ProductPO record);

    List<ProductPO> selectByCriteriaWithBLOBs(ProductCriteria example);

    List<ProductPO> selectByCriteria(ProductCriteria example);

    ProductPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ProductPO record, @Param("example") ProductCriteria example);

    int updateByCriteriaWithBLOBs(@Param("record") ProductPO record, @Param("example") ProductCriteria example);

    int updateByCriteria(@Param("record") ProductPO record, @Param("example") ProductCriteria example);

    int updateByPrimaryKeySelective(ProductPO record);

    int updateByPrimaryKeyWithBLOBs(ProductPO record);

    int updateByPrimaryKey(ProductPO record);
}