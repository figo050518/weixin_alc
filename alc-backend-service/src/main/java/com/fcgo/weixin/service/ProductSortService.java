package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.ProductSortConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductSortBo;
import com.fcgo.weixin.model.backend.req.ProductSortListReq;
import com.fcgo.weixin.persist.dao.ProductSortMapper;
import com.fcgo.weixin.persist.model.ProductSort;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductSortService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductSortMapper productSortMapper;

    public Map<Integer, ProductSort> buildIdProductSort(Set<Integer> ids){
        List<ProductSort> list =productSortMapper.selectAllByIds(ids);
        if (Objects.isNull(list)){
            return null;
        }
        return list.stream().collect(Collectors.toMap(ProductSort::getId, Function.identity()));
    }

    public List<ProductSortBo> getAll(){

        List<ProductSort> list = productSortMapper.selectAll();
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        List<ProductSortBo> boList = list.stream().map(ProductSortConvert::do2Bo4Select).collect(Collectors.toList());
        return boList;
    }

    public PageResponseBO<ProductSortBo> getList(ProductSortListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();

        PageResponseBO.PageResponseBOBuilder boBuilder = PageResponseBO.builder()
                .currentPage(page)
                .pageSize(pageSize);
        ProductSort condition = null;
        int total = productSortMapper.selectCntByCondition(condition);

        if (total==0){
            return boBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<ProductSort> list = productSortMapper.selectAllByCondtion(condition,offset,pageSize );
        List<ProductSortBo> boList = list.stream().map(ProductSortConvert::do2Bo).collect(Collectors.toList());

        int totalPage = PageHelper.getPageTotal(total, pageSize);
        boBuilder.list(boList).total(total).totalPage(totalPage);
        return boBuilder.build();
    }

    public int add(ProductSortBo req){
        ProductSort condition = ProductSortConvert.bo2Do4Insert(req);
        int rows = productSortMapper.insertSelective(condition);
        logger.info("product sort req {} id {}", req, condition.getId());
        return rows;
    }

    public int update(ProductSortBo req){
        logger.info("product sort update {}", req);
        if (req.getId() == null){
            throw new ServiceException(401, "id不合法");
        }
        ProductSort condition = ProductSortConvert.bo2Do4Update(req);
        int rows = productSortMapper.updateByPrimaryKeySelective(condition);
        return rows;
    }
}
