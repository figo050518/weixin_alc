package com.fcgo.weixin.application.user;

import com.fcgo.weixin.persist.po.OperLoginPO;


public interface OperLoginService {
	/**
	 * 运营人员登录
	 * @param userId
	 * @param password
	 * @return
	 */
    public boolean validateLogin(String userId,String password);
    /**
     * 更新
     * @param operLoginPO
     * @return
     */
    public boolean updateOperLogin(OperLoginPO operLoginPO);
    /**
     * 根据userId 查询运营人员信息
     * @param userId
     * @return
     */
	public OperLoginPO getOperLoginById(String userId);
	
	/**
     * 保存
     * @param operLoginPO
     * @return
     */
    public OperLoginPO saveOperLogin(OperLoginPO operLoginPO);
    
}
