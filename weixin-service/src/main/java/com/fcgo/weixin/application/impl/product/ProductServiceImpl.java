package com.fcgo.weixin.application.impl.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.product.IProductService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.generate.criteria.ProductCriteria;
import com.fcgo.weixin.persist.generate.criteria.ProductCriteria.Criteria;
import com.fcgo.weixin.persist.po.ProductImagePO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;
import com.google.common.collect.Lists;

/**
 * @author Ww
 */
@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private IProductDAO productDao;
    @Autowired
    private IProductImageDAO imageDao;
    @Autowired
    private IProductSpecDAO specDao;

    @Override
    public ProductPO getById(int productId) {
        ProductPO productPO = new ProductPO();
        try {
            productPO = productDao.selectByPrimaryKey(productId);
        }
        catch (Exception e) {
            // TODO: handle exception
            logger.info(e.toString());
        }
        return productPO;
    }

    @Override
    public List<ProductPO> pdtProductList(ProductPO productPO, Page page) {
        // TODO Auto-generated method stub
        // 查询记录
        Map parm = new HashMap();
        if (StringUtils.isNotEmpty(productPO.getProName())) {
            parm.put("PRO_NAME", "%" + productPO.getProName() + "%");
        }
        parm.put("GROUP_ID", productPO.getGroupId());
        parm.put("fcg_product_type", productPO.getFcgProductId());
        parm.put("UP_STATE", productPO.getUpState());
        parm.put("SHOP_ID", productPO.getShopId());
        parm.put("FROM_TYPE", productPO.getFromType());

        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        List<ProductPO> productPOs = productDao.pdtProductList(parm);

        // 查询记录条数
        ProductCriteria productCriteria = new ProductCriteria();
        if (StringUtils.isNotEmpty(productPO.getProName())) {
            productCriteria.createCriteria().andProNameLike("%" + productPO.getProName() + "%");
        }
        if (productPO.getGroupId() != null) {
            productCriteria.createCriteria().andGroupIdEqualTo(productPO.getGroupId());
        }
        if (productPO.getFcgProductId() != null) {
            productCriteria.createCriteria().andFcgProductIdEqualTo(productPO.getFcgProductId());
        }
        if (productPO.getUpState() != null) {
            productCriteria.createCriteria().andUpStateEqualTo(productPO.getUpState());
        }
        if (productPO.getShopId() != null) {
            productCriteria.createCriteria().andShopIdEqualTo(productPO.getShopId());
        }
        if (productPO.getFromType() != null) {
            productCriteria.createCriteria().andFromTypeEqualTo(productPO.getFromType());
        }
        int cnt = productDao.countByCriteria(productCriteria);

        page.setRow(productPOs);
        page.setRecords(cnt);
        return productPOs;
    }

    @Override
    public boolean productActive(ProductPO productPO) {
        // TODO Auto-generated method stub
        int cnt = productDao.updateByPrimaryKey(productPO);
        return cnt > 0;
    }

    @Override
    @Transactional
    public int addProduct(ProductPO pro,List<ProductSpecPO> spList,List<String> imgUrls) {
        Date d = new Date();
        // 保存商品
        pro.setCreateTime(d);
        pro.setIsDelete(0);
        pro.setUpdateTime(d);
        pro.setUpState(1);
        pro.setUpTime(d);
        pro.setFromType(1);
        pro.setSalesCount(0);
        List<BigDecimal> prices = Lists.newArrayList();
        // 规格
        for (ProductSpecPO productSpecPO : spList) {
            prices.add(productSpecPO.getSalesPrice());
        }
        // 价格排序
        Collections.sort(prices, new BigDecimalComparator());
        pro.setMinPrice(prices.get(0));
        pro.setMaxPrice(prices.get(prices.size() - 1));
        int i = productDao.insert(pro);
        List<ProductImagePO> imgList = Lists.newArrayList();
        if(!imgUrls.isEmpty()){
        	for(int index =0;index<imgUrls.size();index++){
        		if(imgUrls.get(index)==null){
        			continue;
        		}
        		ProductImagePO image = new ProductImagePO();
               image.setCreateName(pro.getCreateName());
               image.setCreateTime(d);
               image.setDisplayOrder(index);
               image.setImgUrl(imgUrls.get(index));
               image.setIsDelete(0);
               image.setProductId(pro.getId());
               image.setUpdateName(pro.getUpdateName());
               image.setUpdateTime(d);
               imgList.add(image);
			}
        }
        	imageDao.batchInsert(imgList);
        // 规格
        for (ProductSpecPO productSpecPO : spList) {
            productSpecPO.setProductId(pro.getId());
            productSpecPO.setCreateTime(d);
            productSpecPO.setOriginalPrice(productSpecPO.getSalesPrice());
            productSpecPO.setUpdateTime(d);
            productSpecPO.setCreateName(pro.getCreateName());
            productSpecPO.setUpdateName(pro.getUpdateName());
            productSpecPO.setIsDelete(0);
        }
        specDao.batchInsert(spList);
        return i;
    }

    
    @Override
	public int addPlatProduct(ProductPO pro, Object object, List<String> imgUrlList) {
    	 Date d = new Date();
         // 保存商品
         pro.setCreateTime(d);
         pro.setIsDelete(0);
         pro.setUpdateTime(d);
         pro.setUpState(1);
         pro.setUpTime(d);
         pro.setSalesCount(0);
         int i = productDao.insert(pro);
         List<ProductImagePO> imgList = Lists.newArrayList();
         if(!imgUrlList.isEmpty()){
         	for(int index =0;index<imgUrlList.size();index++){
         		if(imgUrlList.get(index)==null){
         			continue;
         		}
         		ProductImagePO image = new ProductImagePO();
                image.setCreateName(pro.getCreateName());
                image.setCreateTime(d);
                image.setDisplayOrder(index);
                image.setImgUrl(imgUrlList.get(index));
                image.setIsDelete(0);
                image.setProductId(pro.getId());
                image.setUpdateName(pro.getUpdateName());
                image.setUpdateTime(d);
                imgList.add(image);
 			}
         }
         imageDao.batchInsert(imgList);
         // 规格
//         for (ProductSpecPO productSpecPO : spList) {
//             productSpecPO.setProductId(pro.getId());
//             productSpecPO.setCreateTime(d);
//             productSpecPO.setOriginalPrice(productSpecPO.getSalesPrice());
//             productSpecPO.setUpdateTime(d);
//             productSpecPO.setCreateName(pro.getCreateName());
//             productSpecPO.setUpdateName(pro.getUpdateName());
//             productSpecPO.setIsDelete(0);
//         }
//         specDao.batchInsert(spList);
		return i;
	}

	@Override
    public int updateSelfProduct(ProductPO pro,List<ProductSpecPO> spList, List<ProductImagePO> list) {
        Date d = new Date();
        // 保存商品
        pro.setUpdateTime(d);
        ProductCriteria cri = new ProductCriteria();
        cri.createCriteria().andIdEqualTo(pro.getId());
        int i = productDao.updateByCriteriaSelective(pro, cri);
        List<ProductImagePO> inserImages =Lists.newArrayList();
        List<ProductImagePO> updateImages = Lists.newArrayList();
        for (int index=0;index<list.size();index++) {
			if(list.get(index).getId()==null || list.get(index).getId()==0 ){
	            String imageUrl =list.get(index).getImgUrl() ;
	            if(imageUrl.isEmpty()){
	            	continue;
	            }
	            ProductImagePO image = new ProductImagePO();
	            image.setCreateName(pro.getCreateName());
	            image.setCreateTime(d);
	            image.setDisplayOrder(index);
	            image.setImgUrl(imageUrl);
	            image.setIsDelete(0);
	            image.setProductId(pro.getId());
	            image.setUpdateName(pro.getUpdateName());
	            image.setUpdateTime(d);
	            inserImages.add(image);
			}
			if(list.get(index)!=null &&list.get(index).getId()!=null&&list.get(index).getId()>0 ){
	            String imageUrl =list.get(index).getImgUrl() ;
	            ProductImagePO image = new ProductImagePO();
	            image.setDisplayOrder(index);
	            image.setImgUrl(imageUrl);
	            image.setUpdateName(pro.getUpdateName());
	            image.setUpdateTime(d);
	            image.setId(list.get(index).getId());
	            updateImages.add(image);
			}
		}
        if(!inserImages.isEmpty()){
        	imageDao.batchInsert(inserImages);
        }
        if(!updateImages.isEmpty()){
        	imageDao.batchUpdate(updateImages);
        }
        // 规格(没有ID 的直接新增，有ID的记录list，并且去删除原来记录中不在这个list的数据)
        List<ProductSpecPO> list2 = new ArrayList<ProductSpecPO>();
        List<Integer> ids = new ArrayList<Integer>();
        List<ProductSpecPO> listOriUpdate = Lists.newArrayList();
        for (ProductSpecPO productSpecPO : spList) {
            if (productSpecPO.getId() == null || productSpecPO.getId() == 0) {
                productSpecPO.setProductId(pro.getId());
                productSpecPO.setCreateTime(d);
                productSpecPO.setUpdateTime(d);
                productSpecPO.setCreateName(pro.getUpdateName());
                productSpecPO.setUpdateName(pro.getUpdateName());
                productSpecPO.setIsDelete(0);
                list2.add(productSpecPO);
            }
            else {
                if (productSpecPO.getId() > 0) {
                    ids.add(productSpecPO.getId());
                    productSpecPO.setUpdateTime(new Date());
                    productSpecPO.setUpdateName(pro.getUpdateName());
                    listOriUpdate.add(productSpecPO);
                }
            }

        }
        if (!ids.isEmpty()) {
            specDao.deleteNotExist(ids, pro.getId());
            specDao.batchUpdate(listOriUpdate);
        }
        if (!list2.isEmpty()) {
            specDao.batchInsert(list2);
        }
        return i;
    }

    @Override
    public int upStateUpdate(ProductPO product, Integer productId) {
        ProductCriteria cri = new ProductCriteria();
        cri.createCriteria().andIdEqualTo(productId);
        return productDao.updateByCriteriaSelective(product, cri);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<ProductPO> findProductList(ProductPO product, Page page) {
        Map parm = new HashMap();
        if (StringUtils.isNotEmpty(product.getProName())) {
            parm.put("prodName", "%" + product.getProName() + "%");
        }
        if(product.getGroupId()!=null && product.getGroupId()>0){
            parm.put("groupId", product.getGroupId());
        }
        if(product.getFcgCategoryId()!=null && product.getFcgCategoryId()!=0){
            parm.put("fcgCategoryId", product.getFcgCategoryId());
        }
        parm.put("fcgProductId", product.getFcgProductId());
        parm.put("upState", product.getUpState());
        parm.put("shopId", product.getShopId());
        parm.put("fromType", product.getFromType());
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        List<ProductPO> productPOs = productDao.pdtProductList(parm);
        return productPOs;
    }

    @Override
    public int countProductList(ProductPO product) {
        // 查询记录条数
        ProductCriteria productCriteria = new ProductCriteria();
        Criteria c = productCriteria.createCriteria();
        if (StringUtils.isNotEmpty(product.getProName())) {
            c.andProNameLike("%" + product.getProName() + "%");
        }
        if (product.getGroupId() != null && product.getGroupId()>0) {
            c.andGroupIdEqualTo(product.getGroupId());
        }
        if (product.getFcgProductId() != null) {
            c.andFcgProductIdEqualTo(product.getFcgProductId());
        }
        if (product.getUpState() != null) {
            c.andUpStateEqualTo(product.getUpState());
        }
        if (product.getShopId() != null) {
            c.andShopIdEqualTo(product.getShopId());
        }
        if (product.getFromType() != null) {
            c.andFromTypeEqualTo(product.getFromType());
        }
            c.andIsDeleteEqualTo(0);
        int cnt = productDao.countByCriteria(productCriteria);
        return cnt;
    }

    @Override
    public int batchUpStateUpdate(List<ProductPO> lists) {
        productDao.batchUpStateUpdate(lists);
        return 0;
    }

    @Override
    public int batchDelete(List<ProductPO> lists) {
        return productDao.batchDelete(lists);
    }

    @Override
    public int updateProductGroup(ProductPO product) {
        ProductCriteria cri = new ProductCriteria();
        cri.createCriteria().andIdEqualTo(product.getId());
        return productDao.updateByCriteriaSelective(product, cri);
    }

    // bigDecimal 比较器
    static class BigDecimalComparator implements Comparator<BigDecimal> {
        public int compare(BigDecimal arg0, BigDecimal arg1) {
            return arg0.compareTo(arg1);
        }
    }

}
