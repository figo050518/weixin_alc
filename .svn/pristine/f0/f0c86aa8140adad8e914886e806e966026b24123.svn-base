package com.fcgo.weixin.controller.product.convert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.common.constants.ProductSource;
import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.ProductDTO;
import com.fcgo.weixin.dto.ProductGroupDTO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.fcg.FcgProduct;
import com.google.common.collect.Lists;

@Service
public class FcgProductConvert implements Convert<FcgProduct, ProductDTO> {
	
	
	

    @Override
    public ProductDTO convertToDTO(FcgProduct d) {
        ProductDTO p = new ProductDTO();
        p.setFcgCateId(d.getF_cate_id());
        p.setFcgCateName("");
        p.setFcgProductId(d.getId());
        p.setFcgProductType(Integer.valueOf(d.getF_texes()));
        p.setFreight(null);
        p.setFromType(Integer.valueOf(ProductSource.PLATFORMSALE.getKey()));
        p.setGroup(null);
        List<String> imgUrlList = Lists.newArrayList();
        imgUrlList.add(d.getF_picurl_logo());
        p.setImgUrlList(imgUrlList);
        p.setMaxPrice(d.getF_price());
        p.setMinPrice(d.getF_price());
        p.setProductDesc("");
        p.setProductName(d.getF_goods_name());
        return p;
    }

    @Override
    public FcgProduct convertToDomain(ProductDTO c) {
    	FcgProduct p = new FcgProduct();
    	
        return p;
    }

    @Override
    public List<ProductDTO> convertCollectionToDTO(List<FcgProduct> domains) {
    	 // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FcgProduct> convertCollectionToDomain(List<ProductDTO> clients) {
        // TODO Auto-generated method stub
        return null;
    }





	

}
