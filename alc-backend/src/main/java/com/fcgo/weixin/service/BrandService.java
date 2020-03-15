package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.BrandAddressConvert;
import com.fcgo.weixin.convert.BrandConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.BrandAddressBo;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.model.backend.req.BrandListReq;
import com.fcgo.weixin.persist.dao.BrandMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.BrandAddress;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandAddressService brandAddressService;

    public List<BrandBo> getAll(){
        List<Brand> dolist = brandMapper.selectAllBrand();
        if (CollectionUtils.isEmpty(dolist)){
            return null;
        }
        List<BrandBo> bos = dolist.stream().map(BrandConvert::do2SimpleBo)
                .collect(Collectors.toList());
        return bos;
    }

    public PageResponseBO<BrandBo> getList(BrandListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<BrandBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        int total = brandMapper.selectCnt();
        if (total==0){
            return pageBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<Brand> dolist = brandMapper.selectAll(offset, pageSize);
        Set<Integer> brandIds = dolist.stream().map(Brand::getId).collect(Collectors.toSet());
        Map<Integer,BrandAddress> brandAddressMap = brandAddressService.buildBrandAddressMap(brandIds);
        List<BrandBo> bos = dolist.stream()
                .map(brand->convertList(brand, brandAddressMap))
                .collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }

    static BrandBo convertList(Brand brand, Map<Integer, BrandAddress> brandAddressMap){
        BrandAddress brandAddress = brandAddressMap.get(brand.getId());
        BrandBo bo = BrandConvert.do2Bo(brand);
        if (Objects.nonNull(brandAddress)){
            BrandAddressBo brandAddressBo = BrandAddressConvert.do2Bo(brandAddress);
            bo.setBrandAddress(brandAddressBo);
        }
        return bo;
    }


    public int add(BrandBo bo){
        int rows = 0;
        if (StringUtils.isBlank(bo.getName())){
            throw new ServiceException(400, "名称不能为空");
        }
        if (StringUtils.isBlank(bo.getPicUrl())){
            throw new ServiceException(400, "图片不能为空");
        }
        Brand brand = BrandConvert.bo2Do4Insert(bo);
        rows = brandMapper.insertSelective(brand);
        logger.info("add brand,req {} id {}", bo, brand.getId());
        return rows;
    }

    public int update(BrandBo bo){
        Integer id;
        if (Objects.isNull(id=bo.getId())){
            throw new ServiceException(400, "ID不能为空");
        }
        Brand condition = BrandConvert.bo2Do4Update(bo);
        int rows = brandMapper.updateByPrimaryKeySelective(condition);
        return rows;
    }

    public Map<Integer,Brand> getIdBrandMap(Set<Integer> brandIdSet){
        final Map<Integer,Brand> idBrandMap = new HashMap<>(brandIdSet.size());
        if (CollectionUtils.isNotEmpty(brandIdSet)) {
            List<Brand> brandList = brandMapper.selectByIds(brandIdSet);
            brandList.forEach(brand -> idBrandMap.put(brand.getId(), brand));
        }
        return idBrandMap;
    }
}
