package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.ProductConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.backend.req.ProductAuditReq;
import com.fcgo.weixin.model.backend.req.ProductCtrlShelveReq;
import com.fcgo.weixin.model.backend.req.ProductListReq;
import com.fcgo.weixin.model.constant.PrdAuditStatus;
import com.fcgo.weixin.model.constant.PrdShelfStatus;
import com.fcgo.weixin.persist.dao.ProductMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.Product;
import com.fcgo.weixin.persist.model.ProductSort;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductSortService productSortService;

    public PageResponseBO<ProductBo> getList(ProductListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<ProductBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        Integer uid;
        if (Objects.isNull(uid = req.getUid())){
            throw new ServiceException(401,"uid不正确");
        }
        String userName;
        if (StringUtils.isBlank(userName=req.getUserName())){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = accountService.isAdmin(uid, userName);
        final String prdName = StringUtils.isBlank(req.getProductName()) ? null : req.getProductName().trim();
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        Supplier<Integer> totalSupplier;
        Supplier<List<Product>> prdListSupplier;
        if (isAdmin){
            logger.info("get product list user is admin, req {}", req);
            Product condition = Product.builder().name(prdName).build();
            totalSupplier = ()-> productMapper.selectCnt(condition);
            prdListSupplier = () -> productMapper.selectAll(condition, offset, pageSize);
        }else{
            Integer brandId;
            if (Objects.isNull(brandId=req.getBrandId()) || brandId<1){
                throw new ServiceException(401, "品牌ID不正确");
            }
            logger.info("get product list user is brand, req {}", req);
            Product condition = Product.builder().brandId(req.getBrandId()).name(prdName).build();
            totalSupplier = ()->  productMapper.selectCnt(condition);
            prdListSupplier = () -> productMapper.selectAll(condition, offset, pageSize);
        }

        int total = totalSupplier.get();
        if (total==0){
            return pageBuilder.build();
        }

        List<Product> dolist = prdListSupplier.get();
        Set<Integer> brandIds = new HashSet<>(dolist.size());
        Set<Integer> sortIds = new HashSet<>(dolist.size());
        dolist.stream().forEach(product -> {
            brandIds.add(product.getBrandId());
            sortIds.add(product.getProductSort());
        });
        Map<Integer, ProductSort> productSortMap = productSortService.buildIdProductSort(sortIds);
        Map<Integer, Brand> brandMap = brandService.getIdBrandMap(brandIds);
        List<ProductBo> bos = dolist.stream().map(product->{
            Brand brand = brandMap.get(product.getBrandId());
            ProductSort productSort = productSortMap.get(product.getProductSort());
            return ProductConvert.do2Bo(product, brand, productSort );
        }).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }




    public int add(ProductBo req){
        Product condition = ProductConvert.bo2Do4Insert(req);
        int rows = productMapper.insertSelective(condition);
        return condition.getId();
    }

    public int update(ProductBo req){
        Integer id;
        if (Objects.isNull(id=req.getId()) || id<1){
            throw new ServiceException(401,"id不正确");
        }
        Product condition = ProductConvert.bo2Do4Update(req);
        int rows = productMapper.updateByPrimaryKeySelective(condition);
        return rows;
    }

    public int audit(ProductAuditReq req){
        Integer uid;
        if (Objects.isNull(uid = req.getUid())){
            throw new ServiceException(401,"uid不正确");
        }
        String userName;
        if (StringUtils.isBlank(userName=req.getUserName())){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = accountService.isAdmin(uid, userName);
        if (!isAdmin){
            throw new ServiceException(401, "非法用户");
        }
        Integer productId;
        if (Objects.isNull(productId=req.getProductId()) || productId<1){
            throw new ServiceException(401,"商品id不正确");
        }
        Integer verifyStatusCode = req.getVerifyStatus();
        PrdAuditStatus prdAuditStatus ;
        if (Objects.isNull(verifyStatusCode)
                || Objects.isNull(prdAuditStatus = PrdAuditStatus.getStatus(verifyStatusCode))){
            throw new ServiceException(401,"审核状态数值不对");
        }
        PrdAuditStatus exceptAuditStatus = null;
        switch (prdAuditStatus){
            case PASS:
                exceptAuditStatus = PrdAuditStatus.INIT;
                break;

            case REJECT:
                exceptAuditStatus = PrdAuditStatus.INIT;
                break;
            default:
                throw new ServiceException(401,"审核状态数值不对");

        }
        Product condition = Product.builder()
                .id(productId)
                .verifyStatus(verifyStatusCode)
                .exceptVerifyStatus(exceptAuditStatus.getCode()).build();
        int row = productMapper.updateAuditStatus(condition);
        return row;
    }

    public int onOffShelve(ProductCtrlShelveReq req){
        Integer productId;
        if (Objects.isNull(productId=req.getProductId()) || productId<1){
            throw new ServiceException(401,"商品id不正确");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (Objects.isNull(product)){
            throw new ServiceException(400, "商品不存在");
        }
        PrdShelfStatus target = PrdShelfStatus.getStatus(req.getStatus());
        int result = 0;
        switch (target){
            case ON:{
                result = onShelve(req, target, product);
                break;
            }
            case OFF:{
                result = offShelve(req, target, product);
                break;
            }
             default:
                 throw new ServiceException(400,"请正确设置状态");

        }
        return result;
    }


    public int onShelve(ProductCtrlShelveReq req,
                        PrdShelfStatus target,
                        Product product){
        logger.info("in onShelve {} {}", req,target);
        if (target.getCode() == product.getStatus()){
            throw new ServiceException(400, "商品不需要再次上架");
        }
        PrdShelfStatus exceptShelfStatus = PrdShelfStatus.getStatus(product.getStatus());
        if (exceptShelfStatus==null){
            throw new ServiceException(400, "商品状态异常");
        }
        List<PrdShelfStatus> legalStatus = Lists.newArrayList(PrdShelfStatus.INIT,
                PrdShelfStatus.OFF);
        if (!legalStatus.contains(exceptShelfStatus)){
            throw new ServiceException(400, "商品状态异常");
        }
        Integer productId = product.getId();
        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;

        Product condition = Product.builder().id(productId)
                .verifyStatus(exceptAuditStatus.getCode())
                .status(target.getCode())
                .exceptStatus(product.getStatus())
                .build();

        return productMapper.updateShelveStatus(condition);
    }

    public int offShelve(ProductCtrlShelveReq req,
                         PrdShelfStatus target,
                         Product product){
        logger.info("in offShelve {} {}", req,target);
        Integer productId = req.getProductId();

        if (target.getCode() == product.getStatus()){
            throw new ServiceException(400, "商品不需要再次下架");
        }
        PrdShelfStatus exceptShelfStatus = PrdShelfStatus.getStatus(product.getStatus());
        if (exceptShelfStatus==null){
            throw new ServiceException(400, "商品状态异常");
        }
        List<PrdShelfStatus> legalStatus = Lists.newArrayList(PrdShelfStatus.ON);
        if (!legalStatus.contains(exceptShelfStatus)){
            throw new ServiceException(400, "商品状态异常");
        }

        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;

        Product condition = Product.builder().id(productId)
                .verifyStatus(exceptAuditStatus.getCode())
                .status(target.getCode())
                .exceptStatus(product.getStatus())
                .build();

        return productMapper.updateShelveStatus(condition);
    }
}
