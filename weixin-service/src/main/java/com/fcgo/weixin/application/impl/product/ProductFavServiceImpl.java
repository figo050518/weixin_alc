package com.fcgo.weixin.application.impl.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.product.ProductFavService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.dao.IUserProductFavDAO;
import com.fcgo.weixin.persist.generate.criteria.UserProductFavCriteria;
import com.fcgo.weixin.persist.po.UserProductFavPO;

@Service
@Transactional
public class ProductFavServiceImpl implements ProductFavService {

    @Autowired
    private IUserProductFavDAO userProductFavDAO;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<UserProductFavPO> listUserProductFav(int userId, Page page) {
        Map parm = new HashMap();
        parm.put("userId", userId);
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        return userProductFavDAO.listUserProductFav(parm);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int countUserProductFav(int userId) {
        Map parm = new HashMap();
        parm.put("userId", userId);
        return userProductFavDAO.countUserProductFav(parm);
    }

    @Override
    public String addProductFav(UserProductFavPO userProductFavPO) {
        UserProductFavCriteria userProductFavCriteria = new UserProductFavCriteria();
        userProductFavCriteria.createCriteria().andProductIdEqualTo(userProductFavPO.getProductId())
                .andIsDeleteEqualTo(0);

        List<UserProductFavPO> userProductFavPOs = userProductFavDAO.selectByCriteria(userProductFavCriteria);
        if (userProductFavPOs != null && userProductFavPOs.size() > 0) {
            // 做取消动作
            userProductFavPOs.get(0).setIsDelete(1);
            userProductFavDAO.updateByPrimaryKey(userProductFavPOs.get(0));
            return "cancelSuccess";
        }
        int i = userProductFavDAO.insert(userProductFavPO);
        if (i > 0) {
            return "addSuccess";
        }
        return "error";
    }

    @Override
    public boolean deleteProductFav(int id) {
        UserProductFavPO userProductFavPO = new UserProductFavPO();
        userProductFavPO.setIsDelete(1);
        userProductFavPO.setUpdateTime(new Date());
        UserProductFavCriteria userProductFavCriteria = new UserProductFavCriteria();
        userProductFavCriteria.createCriteria().andIdEqualTo(id);
        int i = userProductFavDAO.updateByCriteriaSelective(userProductFavPO, userProductFavCriteria);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean getIsFav(int userId, int productId) {
        UserProductFavCriteria userProductFavCriteria = new UserProductFavCriteria();
        userProductFavCriteria.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId)
                .andIsDeleteEqualTo(0);
        int count = userProductFavDAO.countByCriteria(userProductFavCriteria);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
