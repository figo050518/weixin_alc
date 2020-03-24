package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.ProductConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.ProductBo;
import com.fcgo.weixin.model.backend.req.*;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.model.constant.PrdAuditStatus;
import com.fcgo.weixin.model.constant.PrdShelfStatus;
import com.fcgo.weixin.persist.dao.ProductMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.Product;
import com.fcgo.weixin.persist.model.ProductSort;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
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
    private LoginService loginService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductSortService productSortService;

    public PageResponseBO<ProductBo> getList(ProductListReq req) throws SessionExpireException {
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<ProductBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        LoginUserResp userResp = loginService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = AccountService.isAdmin(uid, userName);
        final String prdName = StringUtils.isBlank(req.getProductName()) ? null : req.getProductName().trim();
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        Supplier<Integer> totalSupplier;
        Supplier<List<Product>> prdListSupplier;
        Product condition = Product.builder()
                .name(prdName)
                .productSort(req.getProductSortId())
                .status(req.getStatus())
                .verifyStatus(req.getVerifyStatus())
                .build();
        if (isAdmin){
            logger.info("get product list user is admin, req {}", req);
            condition.setBrandId(req.getBrandId());
            totalSupplier = ()-> productMapper.selectCnt(condition);
            prdListSupplier = () -> productMapper.selectAll(condition, offset, pageSize);
        }else{
            Integer brandId;
            if (Objects.isNull(brandId=userResp.getBrandId()) || brandId<1){
                throw new ServiceException(401, "品牌ID不正确");
            }
            logger.info("get product list user is brand, req {}", req);
            condition.setBrandId(brandId);
            totalSupplier = ()->  productMapper.selectCntByBrandId(condition);
            prdListSupplier = () -> productMapper.selectAllByBrandId(condition, offset, pageSize);
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


    LoginUserResp checkLoginUserIsAdmin() throws SessionExpireException {
        LoginUserResp userResp = loginService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = AccountService.isAdmin(uid, userName);
        if (!isAdmin){
            throw new ServiceException(401, "非法用户");
        }
        return userResp;
    }

    public int add(ProductBo req) throws SessionExpireException {
        Product condition = ProductConvert.bo2Do4Insert(req);
        int rows = productMapper.insertSelective(condition);
        return condition.getId();
    }

    public int update(ProductBo req) throws SessionExpireException {
        Integer id;
        if (Objects.isNull(id=req.getId()) || id<1){
            throw new ServiceException(401,"id不正确");
        }
        LoginUserResp userResp = loginService.getLoginUser();
        if (Objects.isNull(userResp)){
            throw new SessionExpireException();
        }
        Integer uid = userResp.getUid();
        if (Objects.isNull(uid)){
            throw new ServiceException(401,"uid不正确");
        }
        String userName = userResp.getUserName();
        if (StringUtils.isBlank(userName)){
            throw new ServiceException(401, "用户名不正确");
        }
        boolean isAdmin = AccountService.isAdmin(uid, userName);
        Product product = productMapper.selectByPrimaryKey(id);
        if (Objects.isNull(product)){
            throw new ServiceException(401, "商品不存在");
        }
        Integer statusInDB = product.getStatus();
        if (!isAdmin){
            if(Objects.nonNull(statusInDB) && PrdShelfStatus.ON.getCode() == statusInDB.intValue()){
                throw new ServiceException(401, "商品下架后才可以修改");
            }
        }
        Product condition = ProductConvert.bo2Do4Update(req);
        if(!isAdmin){
            condition.setVerifyStatus(PrdAuditStatus.INIT.getCode());
        }
        int rows = productMapper.updateByPrimaryKeySelective(condition);
        return rows;
    }

    public int auditBatch(ProductBatchReq req) throws SessionExpireException {
        List<Integer> ids;
        if (CollectionUtils.isEmpty(ids = req.getIds())){
            throw new ServiceException(401, "没有传入商品ID");
        }
        if (ids.size()>20){
            throw new ServiceException(401, "最多支持20个");
        }
        checkLoginUserIsAdmin();
        Integer verifyStatusCode = req.getVerifyStatus();
        int result = 0;
        for (Integer id: ids){
            result += doAudit(id, verifyStatusCode);
        }
        return result;
    }

    public int audit(ProductAuditReq req) throws SessionExpireException {
        checkLoginUserIsAdmin();
        Integer productId;
        if (Objects.isNull(productId=req.getProductId()) || productId<1){
            throw new ServiceException(401,"商品id不正确");
        }
        Integer verifyStatusCode = req.getVerifyStatus();
        return doAudit( productId, verifyStatusCode);
    }

    private int doAudit(Integer productId,Integer verifyStatusCode) throws SessionExpireException {

        if (Objects.isNull(productId) || productId<1){
            throw new ServiceException(401,"商品id不正确");
        }
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

    public int batchOnOffShelve(ProductCtrlShelveBatchReq batchReq){
        List<Integer> ids = batchReq.getProductIds();
        if (CollectionUtils.isEmpty(ids)){
            logger.warn("batchOnOffShelve getProductIds empty {}", batchReq);
            return -1;
        }
        List<Product> products = productMapper.selectByIds(ids);
        if (CollectionUtils.isEmpty(products)){
            logger.warn("batchOnOffShelve selectByIds from DB empty, {}", batchReq);
            return -1;
        }
        PrdShelfStatus target = PrdShelfStatus.getStatus(batchReq.getStatus());
        int result = 0;
        switch (target){
            case ON:{
                result = batchOnShelve(products, ids, target);
                break;
            }
            case OFF:{
                result = batchOffShelve(products, ids, target);
                break;
            }
            default:
                throw new ServiceException(400,"请正确设置状态");

        }
        return result;
    }
    private int batchOnShelve(List<Product> products, List<Integer> ids,
                              PrdShelfStatus target){
        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;
        Set<Integer> exceptStatusList = Sets.newHashSet();
        for(Product prd : products){
            checkOnShelve(target, prd, exceptAuditStatus);
            exceptStatusList.add(prd.getStatus());
        }
        int rows = productMapper.updateBatchShelveStatus(ids, target.getCode(), exceptStatusList, exceptAuditStatus.getCode());
        logger.info("batch OnShelve ids {} targetStatus {} rows {}", ids, target, rows);
        return rows;
    }

    private int batchOffShelve(List<Product> products, List<Integer> ids,
                              PrdShelfStatus target){
        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;
        Set<Integer> exceptStatusList = Sets.newHashSet();
        for(Product prd : products){
            checkOffShelve(target, prd, exceptAuditStatus);
            exceptStatusList.add(prd.getStatus());
        }
        int rows = productMapper.updateBatchShelveStatus(ids, target.getCode(), exceptStatusList, exceptAuditStatus.getCode());
        logger.info("batch OffShelve ids {} targetStatus {} rows {}", ids, target, rows);
        return rows;
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

    private void checkOnShelve(PrdShelfStatus target,
                               Product product,
                               PrdAuditStatus exceptAuditStatus){
        if (target.getCode() == product.getStatus()){
            throw new ServiceException(400, String.format("商品[%d]不需要再次上架", product.getId()));
        }
        PrdShelfStatus exceptShelfStatus = PrdShelfStatus.getStatus(product.getStatus());
        if (exceptShelfStatus==null){
            throw new ServiceException(400, String.format("商品[%d]状态异常", product.getId()));
        }
        List<PrdShelfStatus> legalStatus = Lists.newArrayList(PrdShelfStatus.INIT,
                PrdShelfStatus.OFF);
        if (!legalStatus.contains(exceptShelfStatus)){
            throw new ServiceException(400, String.format("商品[%d]状态异常", product.getId()));
        }

        Integer verifyStatus;
        if(Objects.nonNull(verifyStatus = product.getVerifyStatus()) && !verifyStatus.equals(exceptAuditStatus.getCode()) ){
            throw new ServiceException(400, String.format("审批通过的商品[%d]才能上架", product.getId()));
        }
    }

    public int onShelve(ProductCtrlShelveReq req,
                        PrdShelfStatus target,
                        Product product){
        logger.info("in onShelve {} {}", req,target);
        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;
        checkOnShelve(target, product, exceptAuditStatus);
        Integer productId = product.getId();

        Product condition = Product.builder().id(productId)
                .verifyStatus(exceptAuditStatus.getCode())
                .status(target.getCode())
                .exceptStatus(product.getStatus())
                .build();

        return productMapper.updateShelveStatus(condition);
    }

    private void checkOffShelve(PrdShelfStatus target,
                                Product product,
                                PrdAuditStatus exceptAuditStatus){
        if (target.getCode() == product.getStatus()){
            throw new ServiceException(400, String.format("商品[%d]不需要再次下架", product.getId()));
        }
        PrdShelfStatus exceptShelfStatus = PrdShelfStatus.getStatus(product.getStatus());
        if (exceptShelfStatus==null){
            throw new ServiceException(400, String.format("商品[%d]状态异常", product.getId()));
        }
        List<PrdShelfStatus> legalStatus = Lists.newArrayList(PrdShelfStatus.ON);
        if (!legalStatus.contains(exceptShelfStatus)){
            throw new ServiceException(400, String.format("商品[%d]状态异常", product.getId()));
        }
        Integer verifyStatus;
        if(Objects.nonNull(verifyStatus = product.getVerifyStatus()) && !verifyStatus.equals(exceptAuditStatus.getCode()) ){
            throw new ServiceException(400, String.format("审批通过的商品[%d]才能下架", product.getId()));
        }
    }

    public int offShelve(ProductCtrlShelveReq req,
                         PrdShelfStatus target,
                         Product product){
        logger.info("in offShelve {} {}", req,target);

        PrdAuditStatus exceptAuditStatus = PrdAuditStatus.PASS;
        checkOffShelve(target, product, exceptAuditStatus);
        Integer verifyStatus;
        if(Objects.nonNull(verifyStatus = product.getVerifyStatus()) && !verifyStatus.equals(exceptAuditStatus.getCode()) ){
            throw new ServiceException(400, "审批通过的商品才能下架");
        }
        Integer productId = req.getProductId();
        Product condition = Product.builder().id(productId)
                .verifyStatus(exceptAuditStatus.getCode())
                .status(target.getCode())
                .exceptStatus(product.getStatus())
                .build();

        return productMapper.updateShelveStatus(condition);
    }
}