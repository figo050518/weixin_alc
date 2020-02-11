package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.OperInfoCriteria;
import com.fcgo.weixin.persist.po.OperInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IOperInfoMapper {
    int countByCriteria(OperInfoCriteria example);

    int deleteByCriteria(OperInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperInfoPO record);

    int insertSelective(OperInfoPO record);

    List<OperInfoPO> selectByCriteria(OperInfoCriteria example);

    OperInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") OperInfoPO record, @Param("example") OperInfoCriteria example);

    int updateByCriteria(@Param("record") OperInfoPO record, @Param("example") OperInfoCriteria example);

    int updateByPrimaryKeySelective(OperInfoPO record);

    int updateByPrimaryKey(OperInfoPO record);
}