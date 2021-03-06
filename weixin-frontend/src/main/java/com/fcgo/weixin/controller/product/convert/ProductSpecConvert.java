package com.fcgo.weixin.controller.product.convert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.ProductSpecDTO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.google.common.collect.Lists;

@Service
public class ProductSpecConvert implements Convert<ProductSpecPO, ProductSpecDTO> {

    @Override
    public ProductSpecDTO convertToDTO(ProductSpecPO d) {
        ProductSpecDTO p = new ProductSpecDTO();
        p.setFcgSpecId(d.getFcoSpecId());
        p.setId(String.valueOf(d.getId()));
        p.setOriginalPrice(d.getOriginalPrice());
        p.setSalesPrice(d.getSalesPrice());
        p.setSpecName(d.getSpecName());
        p.setStock(d.getStock());
        return p;
    }

    @Override
    public ProductSpecPO convertToDomain(ProductSpecDTO c) {
        ProductSpecPO p = new ProductSpecPO();
        p.setFcoSpecId(c.getFcgSpecId());
        p.setId(Integer.valueOf(c.getId()));
        p.setOriginalPrice(c.getOriginalPrice());
        p.setSalesPrice(c.getSalesPrice());
        p.setSpecName(c.getSpecName());
        p.setStock(c.getStock());
        return p;
    }

    @Override
    public List<ProductSpecDTO> convertCollectionToDTO(List<ProductSpecPO> domains) {
        List<ProductSpecDTO> list = Lists.newArrayList();
        for (ProductSpecPO ProductSpecPO : domains) {
            list.add(this.convertToDTO(ProductSpecPO));
        }
        return list;
    }

    @Override
    public List<ProductSpecPO> convertCollectionToDomain(List<ProductSpecDTO> clients) {
        List<ProductSpecPO> list = Lists.newArrayList();
        for (ProductSpecDTO dto : clients) {
            list.add(this.convertToDomain(dto));
        }
        return list;
    }

}
