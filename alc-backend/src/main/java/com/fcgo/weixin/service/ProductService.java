package com.fcgo.weixin.service;

import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.backend.bo.UserBo;
import com.fcgo.weixin.model.backend.req.ProductListReq;
import com.fcgo.weixin.persist.dao.ProductMapper;
import com.fcgo.weixin.persist.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductMapper productMapper;

    public PageResponseBO<ProductBo> getList(ProductListReq req){
        //get from session
        UserBo userBo = null;

        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<ProductBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        Product condition = Product.builder().name(req.getProductName()).build();
        int total = productMapper.selectCnt(condition);
        if (total==0){
            return pageBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<Product> dolist = productMapper.selectAll(condition, offset, pageSize);

        List<ProductBo> bos = dolist.stream().map(product -> {
            ProductBo bo = ProductBo.builder()
                    .name(product.getName())
                    .build();
            return bo;
        }).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }
}
