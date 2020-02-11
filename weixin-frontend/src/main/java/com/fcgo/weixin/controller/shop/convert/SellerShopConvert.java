package com.fcgo.weixin.controller.shop.convert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.SellerShopDTO;
import com.fcgo.weixin.persist.po.SellerShopPO;

@Service
public class SellerShopConvert implements Convert<SellerShopPO, SellerShopDTO> {

    @Override
    public SellerShopDTO convertToDTO(SellerShopPO domain) {
        SellerShopDTO sellerShop = new SellerShopDTO();
        sellerShop.setId(domain.getId());
        sellerShop.setShopName(domain.getShopName());
        sellerShop.setShopDesc(domain.getShopDesc());
        sellerShop.setLogoUrl(domain.getLogoUrlId());
        sellerShop.setBgUrl(domain.getBgUrlId());// 根据imageId获取imageUrl
        return sellerShop;
    }

    @Override
    public SellerShopPO convertToDomain(SellerShopDTO client) {
        SellerShopPO shop = new SellerShopPO();
        shop.setId(client.getId());
        shop.setShopName(client.getShopName());
        shop.setShopDesc(client.getShopDesc());
        shop.setBgUrlId(client.getBgUrl());
        shop.setLogoUrlId(client.getLogoUrl());
        return shop;
    }

    @Override
    public List<SellerShopDTO> convertCollectionToDTO(List<SellerShopPO> domains) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SellerShopPO> convertCollectionToDomain(List<SellerShopDTO> clients) {
        // TODO Auto-generated method stub
        return null;
    }

}
