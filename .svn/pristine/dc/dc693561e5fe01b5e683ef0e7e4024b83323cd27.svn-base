package com.fcgo.weixin.application.user;

import java.util.List;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

public interface UserInfoService {
    /**
     * 注册，插入用户信息表
     * 
     * @param userInfoPO
     * @return
     */
    public boolean register(UserLoginPO userLoginPO, UserInfoPO userInfoPO);

    /**
     * 判断手机号和验证码是否合法
     * 
     * @param telephone
     * @param code
     * @return
     */

    public String validateTelephone(String telephone, String code);

    /**
     * 判断手机号是否存在
     * 
     * @param telephone
     * @return
     */

    public boolean isExistTelephone(String telephone);

    /**
     * 根据手机号查询用户信息
     * 
     * @param telephone
     * @return
     */
    public UserInfoPO getUserInfoByTelephone(String telephone);

    /**
     * @Title: findByFcgId @Description: 根据fcgID获取用户信息 @param @return 参数 @return UserInfoPO 返回类型 @throws
     */
    public UserInfoPO findByFcgId(String fcgId);

    /**
     * 查询卖家信息
     * 
     * @param userInfoPO
     * @return
     */
    public List<UserInfoPO> listUsers(UserInfoPO userInfoPO, Page page);

    /**
     * 根据用户ID 查询用户基本信息
     * 
     * @param id
     * @return
     */
    public UserInfoPO findById(int id);

    /**
     * 用户启用、禁用
     * 
     * @param userInfoPO
     * @return
     */
    public boolean userActive(UserInfoPO userInfoPO);

    public boolean isExistTelephoneByUserId(String telephone, String userId);

    /**
     * 修改昵称
     * 
     * @param nickName
     * @return
     */

    public boolean changeNickName(UserInfoPO userInfoPO);

    /**
     * 修改密码
     * 
     * @param password
     * @return
     */

    public boolean changePassword(UserLoginPO userLoginPO);

    /**
     * 修改电话
     * 
     * @param telephone
     * @return
     */

    public boolean changeTelephone(UserInfoPO userInfoPO, UserLoginPO userLoginPO);

    /**
     * 判断验证码是否合法
     * 
     * @param telephone
     * @param code
     * @return
     */

    public String validateMsgCode(String telephone, String code);

    public UserInfoPO findByOpenId(String openId);
}
