package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.convert.BrandAddressConvert;
import com.fcgo.weixin.dada.domain.merchant.ShopModel;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.backend.bo.BrandAddressBo;
import com.fcgo.weixin.persist.dao.BrandAddressMapper;
import com.fcgo.weixin.persist.dao.BrandMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.BrandAddress;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BrandAddressService {

    @Autowired
    private BrandAddressMapper brandAddressMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProxyService proxyService;

    public int add(BrandAddressBo req){
        Integer brandId;
        if (Objects.isNull(brandId = req.getBrandId())){
            throw new ServiceException(401,"brandId错误");
        }
        BrandAddress brandAddress = BrandAddressConvert.bo2Do4Insert(req);
        brandAddressMapper.insertSelective(brandAddress);
        Integer brandAddressId = brandAddress.getId();
        if (brandAddressId>0){
            Brand brand = brandMapper.selectByPrimaryKey(brandId);
            ShopModel shopAddModel = BrandAddressConvert.convertToShopModel(brandAddress, brand);

            proxyService.addShop(shopAddModel);
        }
        return brandAddressId;
    }

    public int update(BrandAddressBo req){
        Integer id = req.getId();
        if (Objects.isNull(id)){
            throw new ServiceException(401,"Id错误");
        }
        Integer brandId;
        if (Objects.isNull(brandId = req.getBrandId())){
            throw new ServiceException(401,"brandId错误");
        }
        BrandAddress brandAddress = BrandAddressConvert.bo2Do4Update(req);
        brandAddressMapper.updateByPrimaryKeySelective(brandAddress);
        Integer brandAddressId = brandAddress.getId();
        if (brandAddressId>0){
            Brand brand = brandMapper.selectByPrimaryKey(brandId);
            ShopModel shopModel = BrandAddressConvert.convertToShopModel(brandAddress, brand);
            proxyService.updateShop(shopModel);
        }
        return brandAddressId;
    }

    public Map<Integer,BrandAddress> buildBrandAddressMap(Set<Integer> brandIds){
        if (CollectionUtils.isEmpty(brandIds)){
            return Collections.emptyMap();
        }
        List<BrandAddress> list = brandAddressMapper.selectByBrandIds(brandIds);
        if (CollectionUtils.isEmpty(list)){
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(BrandAddress::getBrandId, Function.identity()));
    }
}
