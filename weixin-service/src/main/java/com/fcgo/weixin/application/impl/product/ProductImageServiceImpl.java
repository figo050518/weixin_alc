package com.fcgo.weixin.application.impl.product;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.product.IProductImageService;
import com.fcgo.weixin.persist.generate.criteria.ProductImageCriteria;
import com.fcgo.weixin.persist.po.ProductImagePO;

@Service
public class ProductImageServiceImpl implements IProductImageService {
    private static final Logger logger = LoggerFactory.getLogger(ProductImageServiceImpl.class);
    @Autowired
    private IProductImageDAO productImageDAO;

    @Override
    public List<ProductImagePO> getByProductId(int productId) {
        List<ProductImagePO> list = new ArrayList<ProductImagePO>();
        try {
            ProductImageCriteria productImageCriteria = new ProductImageCriteria();
            productImageCriteria.createCriteria().andProductIdEqualTo(productId).andIsDeleteEqualTo(0);
            list = productImageDAO.selectByCriteria(productImageCriteria);
        }
        catch (Exception e) {
            logger.equals(e);
        }

        return list;

    }

    @Override
    public ProductImagePO getById(int id) {
        return productImageDAO.selectByPrimaryKey(id);
    }

    @Override
    public String getImageUrlByProductId(int productId) {
        ProductImageCriteria productImageCriteria = new ProductImageCriteria();
        productImageCriteria.createCriteria().andProductIdEqualTo(productId).andDisplayOrderEqualTo(1)
                .andIsDeleteEqualTo(0);
        List<ProductImagePO> list = productImageDAO.selectByCriteria(productImageCriteria);
        if (list != null && list.size() > 0) {
            return list.get(0).getImgUrl();
        }
        return null;
    }
}
