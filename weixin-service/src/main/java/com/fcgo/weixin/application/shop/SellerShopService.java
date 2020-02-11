package com.fcgo.weixin.application.shop;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.persist.po.SellerShopPO;

/**
 * @ClassName: SellerShopService
 * @Description: 店铺相关接口
 * @author zhonghui.geng
 * @date 2017年4月5日 上午11:01:21
 */
public interface SellerShopService {

    /**
     * @Title: findById
     * @Description: 根据店铺ID 获取店铺
     * @param 店铺ID
     * @return 店铺
     * @throws
     */
    public SellerShopPO findById(String id);

    /**
     * @Title: save
     * @Description: 保存店铺
     * @param @param sellerShopPO 店铺实体
     * @return boolean 是否保存成功
     * @throws
     */
    public boolean save(SellerShopPO sellerShopPO);

    /**
     * @param fcgUserId
     * @param tel
     * @Title: save （该接口确保事务的一致性）
     * @Description: 保存店铺
     * @param @param sellerShopPO 店铺实体 MultipartFile file 图片文件 头像 MultipartFile file2 图片文件 背景 tel 电话号码 fcgUserId fcg用户ID
     * @return 保存的sellerShop
     * @throws
     */
    public BaseSessionUserDTO save(SellerShopPO sellerShopDTO, String fcgUserId, String token);

    /**
     * @Title: findByParam
     * @Description: 根据参数查找店铺
     * @param @param shopId 店铺ID
     * @param @param sellerId 用户Id
     * @return SellerShopPO 店铺
     * @throws
     */
    public SellerShopPO findByParam(String shopId, String sellerId);

    /**
     * @Title: update
     * @Description: 更新店铺信息
     * @param @param sellerShop 店铺信息
     * @param @return 参数
     * @return int 返回类型
     * @throws
     */
    public int update(SellerShopPO sellerShop);

}
