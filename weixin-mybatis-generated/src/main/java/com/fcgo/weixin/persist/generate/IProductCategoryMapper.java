package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ProductCategoryCriteria;
import com.fcgo.weixin.persist.po.ProductCategoryPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IProductCategoryMapper {
    int countByCriteria(ProductCategoryCriteria example);

    int deleteByCriteria(ProductCategoryCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategoryPO record);

    int insertSelective(ProductCategoryPO record);

    List<ProductCategoryPO> selectByCriteria(ProductCategoryCriteria example);

    ProductCategoryPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ProductCategoryPO record, @Param("example") ProductCategoryCriteria example);

    int updateByCriteria(@Param("record") ProductCategoryPO record, @Param("example") ProductCategoryCriteria example);

    int updateByPrimaryKeySelective(ProductCategoryPO record);

    int updateByPrimaryKey(ProductCategoryPO record);
}