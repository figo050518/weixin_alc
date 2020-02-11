package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ProductGroupCriteria;
import com.fcgo.weixin.persist.po.ProductGroupPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IProductGroupMapper {
    int countByCriteria(ProductGroupCriteria example);

    int deleteByCriteria(ProductGroupCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductGroupPO record);

    int insertSelective(ProductGroupPO record);

    List<ProductGroupPO> selectByCriteria(ProductGroupCriteria example);

    ProductGroupPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ProductGroupPO record, @Param("example") ProductGroupCriteria example);

    int updateByCriteria(@Param("record") ProductGroupPO record, @Param("example") ProductGroupCriteria example);

    int updateByPrimaryKeySelective(ProductGroupPO record);

    int updateByPrimaryKey(ProductGroupPO record);
}