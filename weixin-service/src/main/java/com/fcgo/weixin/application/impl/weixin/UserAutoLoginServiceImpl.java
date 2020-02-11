package com.fcgo.weixin.application.impl.weixin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.weixin.UserAutoLoginService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.persist.dao.IUserInfoDAO;
import com.fcgo.weixin.persist.dao.IUserLoginDAO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

@Service
public class UserAutoLoginServiceImpl implements UserAutoLoginService {

    @Autowired
    private IUserLoginDAO userLoginDAO;
    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    @Transactional
    public BaseSessionUserDTO autoLogin(String openId, String nickName) {
        // 先判断是否有这个OPENiD
        // 根据手机号码查询新增用户信息
        UserInfoPO userInfo = userInfoService.findByOpenId(openId);
        if (userInfo != null) {
            BaseSessionUserDTO baseSessionUserDTO =
                    new BaseSessionUserDTO(userInfo.getTelNum(), userInfo.getNikeName(), true, false, userInfo.getId(),
                            0);
            return baseSessionUserDTO;

        }
        // 新增用户信息表数据
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setTelNum(null);
        userInfoPO.setNikeName(nickName);
        userInfoPO.setUserType(1);
        userInfoPO.setCreateName(nickName);
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setUpdateName(nickName);
        userInfoPO.setUpdateTime(new Date());
        userInfoPO.setIsDelete(0);
        userInfoPO.setWxId(openId);
        userInfoDAO.insert(userInfoPO);
        // 根据手机号码查询新增用户信息
        // 组装新增数据
        UserLoginPO userLoginPO = new UserLoginPO();
        userLoginPO.setUserId(userInfoPO.getId());
        userLoginPO.setTelephone(null);;
        userLoginPO.setPassword(null);
        userLoginPO.setCreateName(nickName);
        userLoginPO.setCreateTime(new Date());
        userLoginPO.setUpdateName(nickName);
        userLoginPO.setUpdateTime(new Date());
        userLoginPO.setIsDelete(0);
        userLoginPO.setWxId(openId);
        userLoginDAO.insert(userLoginPO);
        BaseSessionUserDTO baseSessionUserDTO =
                new BaseSessionUserDTO(userInfoPO.getTelNum(), userInfoPO.getNikeName(), true, false,
                        userInfoPO.getId(), 0);

        return baseSessionUserDTO;
    }

}
