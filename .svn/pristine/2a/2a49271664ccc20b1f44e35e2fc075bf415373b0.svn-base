package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderRefundImageCriteria;
import com.fcgo.weixin.persist.po.OrderRefundImagePO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderRefundImageMapper {
    int countByCriteria(OrderRefundImageCriteria example);

    int deleteByCriteria(OrderRefundImageCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRefundImagePO record);

    int insertSelective(OrderRefundImagePO record);

    List<OrderRefundImagePO> selectByCriteria(OrderRefundImageCriteria example);

    OrderRefundImagePO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderRefundImagePO record, @Param("example") OrderRefundImageCriteria example);

    int updateByCriteria(@Param("record") OrderRefundImagePO record, @Param("example") OrderRefundImageCriteria example);

    int updateByPrimaryKeySelective(OrderRefundImagePO record);

    int updateByPrimaryKey(OrderRefundImagePO record);
}