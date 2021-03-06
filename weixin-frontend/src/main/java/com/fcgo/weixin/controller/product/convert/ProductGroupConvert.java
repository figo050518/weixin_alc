package com.fcgo.weixin.controller.product.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.ProductGroupDTO;
import com.fcgo.weixin.persist.po.ProductGroupPO;

@Service
public class ProductGroupConvert implements  Convert<ProductGroupPO,ProductGroupDTO> {

    @Override
    public ProductGroupDTO convertToDTO(ProductGroupPO domain) {
        ProductGroupDTO group = new ProductGroupDTO();
        group.setGroupName(domain.getGroupName());
        group.setId(domain.getId());
        if(domain.getProductCount()!=null){
          group.setProductCount(domain.getProductCount());  
        }
        return group;
    }

    @Override
    public ProductGroupPO convertToDomain(ProductGroupDTO c) {
        ProductGroupPO p = new ProductGroupPO();
        p.setGroupName(c.getGroupName());
        p.setId(c.getId());
        return p;
    }

    @Override
    public List<ProductGroupDTO> convertCollectionToDTO(List<ProductGroupPO> domains) {
        List<ProductGroupDTO> list = new ArrayList<ProductGroupDTO>();
        for (ProductGroupPO productGroupPO : domains) {
            list.add(this.convertToDTO(productGroupPO));
        }
        return list;
    }

    @Override
    public List<ProductGroupPO> convertCollectionToDomain(List<ProductGroupDTO> clients) {
        List<ProductGroupPO> list = new ArrayList<ProductGroupPO>();
        for (ProductGroupDTO productGroupPO : clients) {
            list.add(this.convertToDomain(productGroupPO));
        }
        return list;
    }

   

    

}
