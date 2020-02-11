package com.fcgo.weixin.application.impl.shop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.persist.dao.ISellerShopDAO;
import com.fcgo.weixin.persist.dao.IUserInfoDAO;
import com.fcgo.weixin.persist.dao.IUserLoginDAO;
import com.fcgo.weixin.persist.generate.criteria.SellerShopCriteria;
import com.fcgo.weixin.persist.po.SellerShopPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

@Service
public class SellerShopServiceImpl implements SellerShopService {
    @Autowired
    private ISellerShopDAO sellerShopDao;
    @Autowired
    private IUserInfoDAO userInfoDao;
    @Autowired
    private IUserLoginDAO userLoginDao;

    @Override
    public SellerShopPO findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return sellerShopDao.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public boolean save(SellerShopPO sellerShopPO) {
        sellerShopPO.setCreateTime(new Date());
        sellerShopPO.setUpdateTime(new Date());
        sellerShopPO.setIsDelete(0);
        int flag = sellerShopDao.insert(sellerShopPO);
        return flag > 0 ? true : false;
    }

    @Transactional
    @Override
    public BaseSessionUserDTO save(SellerShopPO shop, String fcgUserId, String token) {
        Date d = new Date();
        // step1 先插入用户信息
        UserInfoPO userInfo = new UserInfoPO();
        userInfo.setCreateName(null);
        userInfo.setCreateTime(d);
        userInfo.setFcgSellerId(Integer.valueOf(fcgUserId));
        userInfo.setFcgToken(token);
        userInfo.setIsDelete(0);
        userInfo.setNikeName(null);
        userInfo.setTelNum(null);
        userInfo.setUpdateName(null);
        userInfo.setUpdateTime(d);
        userInfo.setUserType(Integer.valueOf(UserType.SELLER.getKey()));
        userInfoDao.insert(userInfo);
        // step2 插入用户登录信息
        UserLoginPO userLogin = new UserLoginPO();
        userLogin.setCreateName(null);
        userLogin.setCreateTime(d);
        userLogin.setIsDelete(0);
        userLogin.setPassword("");
        userLogin.setTelephone(null);
        userLogin.setUpdateName(null);
        userLogin.setUserId(userInfo.getId());
        userLoginDao.insert(userLogin);
        shop.setIsDelete(0);
        shop.setSellerId(userInfo.getId());
        shop.setCreateTime(d);
        shop.setUpdateTime(d);
        shop.setCreateName(null);
        shop.setUpdateTime(null);
        this.save(shop);
        BaseSessionUserDTO baseSessionUserDTO =
                new BaseSessionUserDTO(null, null, false, true, userInfo.getId(), shop.getId());
        return baseSessionUserDTO;

    }

    @Override
    public SellerShopPO findByParam(String shopId, String sellerId) {
        if (!StringUtils.isEmpty(shopId) && !shopId.equals("0")) {
            return this.findById(shopId);
        }
        SellerShopCriteria criteria = new SellerShopCriteria();
        criteria.createCriteria().andSellerIdEqualTo(Integer.valueOf(sellerId));
        List<SellerShopPO> shopList = sellerShopDao.selectByCriteria(criteria);
        if (shopList == null || shopList.isEmpty() || shopList.size() > 1) {
            return null;
        }
        return shopList.get(0);
    }

    @Override
    public int update(SellerShopPO sellerShop) {
        if (sellerShop.getId() == null) {
            return 0;
        }
        SellerShopPO shop = new SellerShopPO();
        shop.setShopName(sellerShop.getShopName());
        shop.setShopDesc(sellerShop.getShopDesc());
        shop.setLogoUrlId(sellerShop.getLogoUrlId());
        shop.setBgUrlId(sellerShop.getBgUrlId());
        SellerShopCriteria cri = new SellerShopCriteria();
        cri.createCriteria().andIdEqualTo(sellerShop.getId());
        return sellerShopDao.updateByCriteriaSelective(shop, cri);
    }

}
