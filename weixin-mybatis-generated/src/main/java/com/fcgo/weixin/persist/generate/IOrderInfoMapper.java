package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderInfoCriteria;
import com.fcgo.weixin.persist.po.OrderInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderInfoMapper {
    int countByCriteria(OrderInfoCriteria example);

    int deleteByCriteria(OrderInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfoPO record);

    int insertSelective(OrderInfoPO record);

    List<OrderInfoPO> selectByCriteria(OrderInfoCriteria example);

    OrderInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderInfoPO record, @Param("example") OrderInfoCriteria example);

    int updateByCriteria(@Param("record") OrderInfoPO record, @Param("example") OrderInfoCriteria example);

    int updateByPrimaryKeySelective(OrderInfoPO record);

    int updateByPrimaryKey(OrderInfoPO record);
}