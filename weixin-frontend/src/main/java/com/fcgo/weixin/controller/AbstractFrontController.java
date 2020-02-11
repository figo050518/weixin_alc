package com.fcgo.weixin.controller;

import java.util.Date;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.dto.OperatorInfoDTO;

/**
 * AbstractBaseController.java
 * 
 * @author xiahan
 */
public abstract class AbstractFrontController {

    /**
     * 获取操作者信息
     * 
     * @param baseDTO
     * @return
     */
    protected OperatorInfoDTO fillOperatorInfoDTO(BaseSessionUserDTO baseSessionUserDTO) {
        OperatorInfoDTO operatorInfoDTO = new OperatorInfoDTO();
        // 获取北京时间
        Date operatorTime = DateUtil.getBJCurrentDate();
        operatorInfoDTO.setAddName(baseSessionUserDTO.getNickName());
        operatorInfoDTO.setCreateTime(operatorTime);
        operatorInfoDTO.setUpdatName(baseSessionUserDTO.getNickName());
        operatorInfoDTO.setUpdateTime(operatorTime);
        return operatorInfoDTO;
    }

}
