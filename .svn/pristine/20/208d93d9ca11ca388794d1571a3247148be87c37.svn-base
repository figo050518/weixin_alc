package com.fcgo.weixin.persist.generate;

import com.fcgo.weixin.persist.generate.criteria.ImageInfoCriteria;
import com.fcgo.weixin.persist.po.ImageInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IImageInfoMapper {
    int countByCriteria(ImageInfoCriteria example);

    int deleteByCriteria(ImageInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImageInfoPO record);

    int insertSelective(ImageInfoPO record);

    List<ImageInfoPO> selectByCriteria(ImageInfoCriteria example);

    ImageInfoPO selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") ImageInfoPO record, @Param("example") ImageInfoCriteria example);

    int updateByCriteria(@Param("record") ImageInfoPO record, @Param("example") ImageInfoCriteria example);

    int updateByPrimaryKeySelective(ImageInfoPO record);

    int updateByPrimaryKey(ImageInfoPO record);
}