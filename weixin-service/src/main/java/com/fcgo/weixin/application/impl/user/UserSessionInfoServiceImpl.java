package com.fcgo.weixin.application.impl.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.user.UserSessionInfoService;
import com.fcgo.weixin.persist.dao.IUserSessionInfoDAO;
import com.fcgo.weixin.persist.generate.criteria.UserSessionInfoCriteria;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;

@Service
public class UserSessionInfoServiceImpl implements UserSessionInfoService {

    @Autowired
    private IUserSessionInfoDAO userSessionInfoDAO;

    @Override
    public String insertSessionInfo(String telephone) {
        // 生成随机tokenId
        String tokenId = UUID.randomUUID().toString();
        // 生成新增对象
        UserSessionInfoPO sessionInfo = new UserSessionInfoPO();
        sessionInfo.setTelephone(telephone);
        sessionInfo.setTokenId(tokenId);
        sessionInfo.setCreateName(telephone);
        sessionInfo.setCreateTime(new Date());
        sessionInfo.setUpdateName(telephone);
        sessionInfo.setUpdateTime(new Date());
        sessionInfo.setDeleteFlag("0");
        sessionInfo.setDeleteName(telephone);
        sessionInfo.setDeleteTime(new Date());
        // 调用新增方法
        userSessionInfoDAO.insert(sessionInfo);
        return tokenId;
    }

    @Override
    public List<UserSessionInfoPO> findBytokenId(String tokenId) {
        UserSessionInfoCriteria userSessionInfoCriteria = new UserSessionInfoCriteria();
        userSessionInfoCriteria.createCriteria().andTokenIdEqualTo(tokenId);
        return userSessionInfoDAO.selectByCriteria(userSessionInfoCriteria);
    }

    @Override
    public void updateByTokenId(String tokenId) {
        UserSessionInfoPO sessionInfo = new UserSessionInfoPO();
        sessionInfo.setTokenId(tokenId);
        sessionInfo.setDeleteFlag("1");
        sessionInfo.setUpdateTime(new Date());
        UserSessionInfoCriteria userSessionInfoCriteria = new UserSessionInfoCriteria();
        userSessionInfoCriteria.createCriteria().andTelephoneEqualTo(tokenId);
        userSessionInfoDAO.updateByCriteriaSelective(sessionInfo, userSessionInfoCriteria);
    }

    @Override
    public List<UserSessionInfoPO> getUsefulByTokenIdAndTepephone(String tokenId, String telephone) {
        UserSessionInfoCriteria userSessionInfoCriteria = new UserSessionInfoCriteria();
        userSessionInfoCriteria.createCriteria().andTokenIdEqualTo(tokenId).andDeleteFlagEqualTo("0")
                .andTelephoneEqualTo(telephone);
        return userSessionInfoDAO.selectByCriteria(userSessionInfoCriteria);
    }

}
