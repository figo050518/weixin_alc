package com.fcgo.weixin.application.impl.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.user.UserAddressService;
import com.fcgo.weixin.persist.dao.IUserAddressDAO;
import com.fcgo.weixin.persist.generate.criteria.UserAddressCriteria;
import com.fcgo.weixin.persist.po.UserAddressPO;

@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private IUserAddressDAO userAddressDao;

    @Override
    public List<UserAddressPO> findList(Integer userId) {
        UserAddressCriteria cri = new UserAddressCriteria();
        cri.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(0);
        List<UserAddressPO> userAddList = userAddressDao.selectByCriteria(cri);
        return userAddList;
    }

    @Override
    public int save(UserAddressPO userAddressPo, Boolean isSeller) {
        if (userAddressPo.getId() != null && userAddressPo.getId() > 0) {
            userAddressPo.setUpdateTime(new Date());
            UserAddressCriteria cri = new UserAddressCriteria();
            cri.createCriteria().andIdEqualTo(userAddressPo.getId());
            return userAddressDao.updateByCriteriaSelective(userAddressPo, cri);
            // return userAddressDao.updateByPrimaryKey(userAddressPo);
        }
        // 判断是否是第一次添加
        UserAddressCriteria cri = new UserAddressCriteria();
        cri.createCriteria().andUserIdEqualTo(userAddressPo.getUserId()).andIsDeleteEqualTo(0);
        List<UserAddressPO> list = userAddressDao.selectByCriteria(cri);
        if (list.size() == 0) {
            userAddressPo.setIsDefault(1);
        }
        else {
            if (isSeller) {
                return 0;
            }
            userAddressPo.setIsDefault(0);
        }
        userAddressPo.setIsDelete(0);
        userAddressPo.setCreateTime(new Date());
        userAddressPo.setCreateName(userAddressPo.getUpdateName());
        userAddressPo.setUpdateTime(new Date());
        return userAddressDao.insert(userAddressPo);
    }

    @Override
    public UserAddressPO findById(Integer Id) {
        return userAddressDao.selectByPrimaryKey(Id);
    }

    @Override
    public void deleteById(Integer userAddrId) {
        userAddressDao.deleteById(userAddrId);

    }

    @Override
    public int setDefault(Integer id, Integer userId) {
        // 原来默认的设置为非默认
        UserAddressCriteria cri = new UserAddressCriteria();
        UserAddressPO addr = new UserAddressPO();
        addr.setIsDefault(0);
        cri.createCriteria().andIsDefaultEqualTo(1).andUserIdEqualTo(userId);
        userAddressDao.updateByCriteriaSelective(addr, cri);
        UserAddressCriteria cri2 = new UserAddressCriteria();
        UserAddressPO addr2 = new UserAddressPO();
        cri2.createCriteria().andIdEqualTo(id);
        addr2.setIsDefault(1);
        return userAddressDao.updateByCriteriaSelective(addr2, cri2);

    }

    @Override
    public UserAddressPO findDefaultByUserId(Integer userId) {
        UserAddressCriteria cri = new UserAddressCriteria();
        cri.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(0).andIsDefaultEqualTo(1);
        List<UserAddressPO> userAddList = userAddressDao.selectByCriteria(cri);
        if (userAddList != null && userAddList.size() > 0) {
            return userAddList.get(0);
        }
        return null;
    }

}
