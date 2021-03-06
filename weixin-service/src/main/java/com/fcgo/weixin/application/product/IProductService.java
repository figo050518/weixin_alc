package com.fcgo.weixin.application.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.ProductImagePO;
import com.fcgo.weixin.persist.po.ProductPO;
import com.fcgo.weixin.persist.po.ProductSpecPO;

/**
 * @author Ww
 */
public interface IProductService {
    /**
     * @param productId
     * @return
     */
    ProductPO getById(int productId);

    /**
     * 分页查询商品列表
     * @param productPO
     * @param page
     * @return
     */
    public List<ProductPO> pdtProductList(ProductPO productPO,Page page);
    /**
     * 上下架
     * @param productPO
     * @return
     */
    public boolean productActive(ProductPO productPO);

   

    /**
     * @param imgUrls 图片链接
    * @Title: addProduct 
    * @Description:上传商品
    * @param  pro 商品信息
    * @param  sepcList 规格信息
    * @param @return    参数
    * @return  int  返回类型
    * @throws
    **/ 
    int addProduct(ProductPO pro, List<ProductSpecPO> spList, List<String> imgUrls);

    /**
     * @param list 图片
    * @Description: 编辑商品 
    * @param @param pro
    * @param @param spList
    * @param @return    参数
    * @return  int  返回类型
    * @throws
     */
    int updateSelfProduct(ProductPO pro, List<ProductSpecPO> spList, List<ProductImagePO> list);

    /**
     * @param productId 
     * 更新上下架状态
    * @Title: upStateUpdate 
    * @param @param product    参数
    * @return  void  返回类型
    * @throws
     */
    int upStateUpdate(ProductPO product, Integer productId);

    /**
    * @param @param product 商品
    * @param @param page 分页
    * @param @return    参数
    * @return  List<ProductPO>  返回类型
    * @throws
     */
    List<ProductPO> findProductList(ProductPO product, Page page);

    /**
    * @param @param product 
    * @param @return    参数
    * @return  Object  返回类型
    * @throws
     */
    int countProductList(ProductPO product);

    /**
    * @param state 
     * @Description: 批量更新
    * @param @param lists
    * @param @return    设定文件
     */
    int batchUpStateUpdate(List<ProductPO> lists);

    /**
     *  gengxin 分组
    * @Description: TODO
    * @param @param product
    * @param @return    设定文件
     */
    int updateProductGroup(ProductPO product);

    /**
     * 批量删除
    * @param @param lists
    * @param @return    设定文件
     */
    int batchDelete(List<ProductPO> lists);

    /**
     * 添加平台商品
     * @param pro
     * @param object
     * @param imgUrlList
     * @return
     */
	int addPlatProduct(ProductPO pro, Object object, List<String> imgUrlList);

    
}
