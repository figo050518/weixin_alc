package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OrderReceiverAddressCriteria;
import com.fcgo.weixin.persist.po.OrderReceiverAddressPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOrderReceiverAddressMapper {
    int countByCriteria(OrderReceiverAddressCriteria example);

    int deleteByCriteria(OrderReceiverAddressCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderReceiverAddressPO record);

    int insertSelective(OrderReceiverAddressPO record);

    List<OrderReceiverAddressPO> selectByCriteria(OrderReceiverAddressCriteria example);

    OrderReceiverAddressPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OrderReceiverAddressPO record, @Param("example") OrderReceiverAddressCriteria example);

    int updateByCriteria(@Param("record") OrderReceiverAddressPO record, @Param("example") OrderReceiverAddressCriteria example);

    int updateByPrimaryKeySelective(OrderReceiverAddressPO record);

    int updateByPrimaryKey(OrderReceiverAddressPO record);
}