package com.fcgo.weixin.controller.product.convert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.ProductDTO;
import com.fcgo.weixin.dto.ProductGroupDTO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.google.common.collect.Lists;

@Service
public class ProductConvert implements Convert<ProductPO, ProductDTO> {

    @Override
    public ProductDTO convertToDTO(ProductPO d) {
        ProductDTO p = new ProductDTO();
        p.setFcgProductId(d.getFcgProductId());
        p.setFcgProductType(d.getFcgProductType());
        p.setFromType(d.getFromType());
        ProductGroupDTO group = new ProductGroupDTO();
        group.setId(d.getGroupId());
        p.setGroup(group);
        p.setId(d.getId());
        p.setMaxPrice(d.getMaxPrice());
        p.setMinPrice(d.getMinPrice());
        p.setFreight(d.getFreight());
        if (d.getProductDesc() != null) {
            try {
                p.setProductDesc(d.getProductDesc());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        p.setProductName(d.getProName());
        p.setSalesCount(d.getSalesCount());
        p.setUpState(d.getUpState());
        p.setFcgCateId(d.getFcgCategoryId());
        p.setFcgCateName(d.getCategoryName());
        return p;
    }

    @Override
    public ProductPO convertToDomain(ProductDTO c) {
        ProductPO p = new ProductPO();
        p.setFcgProductId(c.getFcgProductId());
        p.setFcgProductType(c.getFcgProductType());
        p.setFromType(c.getFromType());
        p.setGroupId(c.getGroup().getId());
        p.setId(c.getId());
        p.setMaxPrice(c.getMaxPrice());
        p.setMinPrice(c.getMinPrice());
        p.setFreight(c.getFreight());
        p.setProductDesc(c.getProductDesc());
        p.setProName(c.getProductName());
        p.setSalesCount(c.getSalesCount());
        p.setUpState(c.getUpState());
        p.setFcgCategoryId(c.getFcgCateId());
        p.setCategoryName(c.getFcgCateName());
        p.setFcgProductId(c.getFcgProductId());
        return p;
    }

    @Override
    public List<ProductDTO> convertCollectionToDTO(List<ProductPO> domains) {
        List<ProductDTO> list = Lists.newArrayList();
        for (ProductPO productPO : domains) {
            list.add(this.convertToDTO(productPO));
        }
        return list;
    }

    @Override
    public List<ProductPO> convertCollectionToDomain(List<ProductDTO> clients) {
        // TODO Auto-generated method stub
        return null;
    }

}
