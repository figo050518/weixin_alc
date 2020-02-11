package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ValidateCodeLogCriteria;
import com.fcgo.weixin.persist.po.ValidateCodeLogPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IValidateCodeLogMapper {
    int countByCriteria(ValidateCodeLogCriteria example);

    int deleteByCriteria(ValidateCodeLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ValidateCodeLogPO record);

    int insertSelective(ValidateCodeLogPO record);

    List<ValidateCodeLogPO> selectByCriteria(ValidateCodeLogCriteria example);

    ValidateCodeLogPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ValidateCodeLogPO record, @Param("example") ValidateCodeLogCriteria example);

    int updateByCriteria(@Param("record") ValidateCodeLogPO record, @Param("example") ValidateCodeLogCriteria example);

    int updateByPrimaryKeySelective(ValidateCodeLogPO record);

    int updateByPrimaryKey(ValidateCodeLogPO record);
}