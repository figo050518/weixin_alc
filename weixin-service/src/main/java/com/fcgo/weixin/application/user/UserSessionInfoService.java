package com.fcgo.weixin.application.user;

import java.util.List;

import com.fcgo.weixin.persist.po.UserSessionInfoPO;

public interface UserSessionInfoService {
    /**
     * 登录插入token
     * 
     * @param telephone
     * @return
     */

    public String insertSessionInfo(String telephone);

    /**
     * 根据主键查询
     * 
     * @param tokenId
     * @return
     */

    public List<UserSessionInfoPO> findBytokenId(String tokenId);

    /**
     * 更新token表
     * 
     * @param tokenId
     */

    public void updateByTokenId(String tokenId);

    /**
     * 根据手机号和主键查询
     * 
     * @param tokenId
     * @param telephone
     * @return
     */

    public List<UserSessionInfoPO> getUsefulByTokenIdAndTepephone(String tokenId, String telephone);

}
