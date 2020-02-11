package com.fcgo.weixin.application.impl.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.product.IProductGroupService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.dao.IProductGroupDAO;
import com.fcgo.weixin.persist.generate.criteria.ProductGroupCriteria;
import com.fcgo.weixin.persist.po.ProductGroupPO;

/**
 * @author Ww
 */
@Service
public class ProductGroupServiceImpl implements IProductGroupService {

    @Autowired
    private IProductGroupDAO productGroupDAO;

	@Override
	public ProductGroupPO getById(int groupId) {
		// TODO Auto-generated method stub
		return productGroupDAO.selectByPrimaryKey(groupId);
	}

	@Override
	public List<ProductGroupPO> pdtProductList(ProductGroupPO productGroupPO,
			Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductGroupPO> pdtProductGroupAllList() {
		// TODO Auto-generated method stub
		ProductGroupCriteria productGroupCriteria = new ProductGroupCriteria();
		return productGroupDAO.selectByCriteria(productGroupCriteria);
	}

    @Override
    public List<ProductGroupPO> findByShopId(Integer shopId) {
        if(shopId==null || shopId==0){
            return null;
        }
        ProductGroupCriteria productGroupCriteria = new ProductGroupCriteria();
        productGroupCriteria.createCriteria().andShopIdEqualTo(shopId).andIsDeleteEqualTo(0);
        return productGroupDAO.selectByCriteria(productGroupCriteria);
    }

    @Override
    public int add(ProductGroupPO p) {
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        p.setIsDelete(0);
        return productGroupDAO.insert(p);
    }

    @Override
    public int delete(Integer id) {
        ProductGroupCriteria cri = new ProductGroupCriteria();
        cri.createCriteria().andIdEqualTo(id);
        ProductGroupPO group = new ProductGroupPO();
        group.setIsDelete(1);
        return productGroupDAO.updateByCriteria(group, cri);
    }

    @Override
    public int update(ProductGroupPO group) {
        ProductGroupCriteria cri = new ProductGroupCriteria();
        cri.createCriteria().andIdEqualTo(group.getId());
        return productGroupDAO.updateByCriteria(group, cri);
    }

    @Override
    public List<ProductGroupPO> findByShopIdWithCount(int shopId,Page page) {
        Map parm = new HashMap();
        parm.put("shopId",shopId);
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        return productGroupDAO.findByShopIdWithCount(parm);
    }
	
    

    
    
    

}
