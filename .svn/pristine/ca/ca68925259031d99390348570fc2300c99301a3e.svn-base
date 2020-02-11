package com.fcgo.weixin.application.user;

import java.util.List;

import com.fcgo.weixin.persist.po.UserAddressPO;

public interface UserAddressService {

    /**
     * @Title: findList
     * @Description: 根据用户ID 获取所有收货地址
     * @param @param userId
     * @param @return 设定文件
     * @return List<UserAddressPO> 返回类型
     * @throws
     */
    List<UserAddressPO> findList(Integer userId);

    /**
     * @param boolean1
     * @Description: 新增用户收货地址
     * @param @param userAddressPo 收货地址
     * @return void 返回类型
     * @throws
     */
    int save(UserAddressPO userAddressPo, Boolean isSeller);

    /**
     * @Description: 根据ID 获取用户地址
     * @param id userAddressID
     * @return UserAddressPO 返回类型
     * @throws
     */
    UserAddressPO findById(Integer id);

    /**
     * @Description: 根据ID 逻辑删除
     * @param @param userAddrId 设定文件
     * @return void 返回类型
     * @throws
     */
    void deleteById(Integer userAddrId);

    /**
     * @Description: 将地址设置为默认
     * @param @param id 参数
     * @return void 返回类型
     * @throws
     */
    int setDefault(Integer id, Integer userId);

    /**
     * 查询用户的默认地址
     * 
     * @param userId
     * @return
     */

    UserAddressPO findDefaultByUserId(Integer userId);

}
