package com.fcgo.weixin.application.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.product.IProductSpecService;
import com.fcgo.weixin.persist.generate.criteria.ProductSpecCriteria;
import com.fcgo.weixin.persist.po.ProductSpecPO;

/**
 * @author Ww
 */
@Service
public class ProductSpecServiceImpl implements IProductSpecService {

    @Autowired
    private IProductSpecDAO productSpecDAO;

    @Override
    public List<ProductSpecPO> getByProductId(int productId) {
        ProductSpecCriteria productSpecCriteria = new ProductSpecCriteria();
        productSpecCriteria.createCriteria().andProductIdEqualTo(productId).andIsDeleteEqualTo(0);
        return productSpecDAO.selectByCriteria(productSpecCriteria);
    }

    @Override
    public List<ProductSpecPO> getByProductIds(List<Integer> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return null;
        }
        return productSpecDAO.findByProductIds(productIds);
    }

    @Override
    public ProductSpecPO getBySpecId(int specId) {

        return productSpecDAO.selectByPrimaryKey(specId);
    }

}
