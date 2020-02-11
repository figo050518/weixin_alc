package com.fcgo.weixin.application.sys;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.OperGroupPO;
import com.fcgo.weixin.persist.po.OperInfoPO;
import com.fcgo.weixin.persist.po.ProductPO;


public interface OperGroupService {

	/**
	 * 根据id查询用户组
	 * @param id
	 * @return
	 */
    public OperGroupPO queryOperGroupById(Integer id);

    /**
     * 分页查询用户组
     * @param operGroupPO
     * @param pageIndex
     * @return
     */
    public Page queryOperGroupListPage(OperGroupPO operGroupPO,int pageIndex);
    /**
     *  保存用户组
     * @param operGroupPO
     * @return
     */
    public boolean saveOperGroup(OperGroupPO operGroupPO) ;
     /**
      * 根据Id删除
      * @param id
      * @return
      */
    public boolean deleteOperGroupById(Integer id);
}
