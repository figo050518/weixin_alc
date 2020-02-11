package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ProductSpecCriteria;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IProductSpecMapper {
    int countByCriteria(ProductSpecCriteria example);

    int deleteByCriteria(ProductSpecCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecPO record);

    int insertSelective(ProductSpecPO record);

    List<ProductSpecPO> selectByCriteria(ProductSpecCriteria example);

    ProductSpecPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ProductSpecPO record, @Param("example") ProductSpecCriteria example);

    int updateByCriteria(@Param("record") ProductSpecPO record, @Param("example") ProductSpecCriteria example);

    int updateByPrimaryKeySelective(ProductSpecPO record);

    int updateByPrimaryKey(ProductSpecPO record);
}