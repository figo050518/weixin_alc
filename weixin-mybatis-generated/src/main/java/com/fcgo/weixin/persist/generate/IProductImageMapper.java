package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ProductImageCriteria;
import com.fcgo.weixin.persist.po.ProductImagePO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IProductImageMapper {
    int countByCriteria(ProductImageCriteria example);

    int deleteByCriteria(ProductImageCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImagePO record);

    int insertSelective(ProductImagePO record);

    List<ProductImagePO> selectByCriteria(ProductImageCriteria example);

    ProductImagePO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ProductImagePO record, @Param("example") ProductImageCriteria example);

    int updateByCriteria(@Param("record") ProductImagePO record, @Param("example") ProductImageCriteria example);

    int updateByPrimaryKeySelective(ProductImagePO record);

    int updateByPrimaryKey(ProductImagePO record);
}