package com.fcgo.weixin.application.sys;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.OperInfoPO;
import com.fcgo.weixin.persist.po.ProductPO;


public interface OperInfoService {

	/**
	 * 根据用户id查询运营人员信息
	 * @param userId
	 * @return
	 */
    public OperInfoPO queryOperInfoById(String userId);

    /**
     * 分页查询运营人员
     * @param operInfoPO
     * @param pageIndex
     * @return
     */
    public Page queryOperInfoListPage(OperInfoPO operInfoPO,int pageIndex);
    /**
     *  根据用户id查询 启用的运营人员信息
     * @param userId
     * @return
     */
    public OperInfoPO queryValidOperInfoById(String userId) ;
    /**
     * 更新
     * @param productPO
     * @return
     */
    public boolean updateOperInfo(OperInfoPO operInfoPO);
    /**
     * 校验用户信息唯一性
     * @param operInfoPO
     * @return
     */
    public boolean validateUniqueOperInfo(OperInfoPO operInfoPO);
    
    /**
     * 保存用户信息
     * @param operInfoPO
     * @return
     */
    public OperInfoPO saveOperInfo(OperInfoPO operInfoPO);
}
