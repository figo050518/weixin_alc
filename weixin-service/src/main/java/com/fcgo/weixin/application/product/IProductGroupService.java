package com.fcgo.weixin.application.product;

import java.util.List;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.ProductGroupPO;
import com.fcgo.weixin.persist.po.ProductPO;

/**
 * @author guangyang
 */
public interface IProductGroupService {
    /**
     * @param productId
     * @return
     */
    ProductGroupPO getById(int groupId);

    /**
     * 分页查询商品分组列表
     * @param productPO
     * @param page
     * @return
     */
    public List<ProductGroupPO> pdtProductList(ProductGroupPO productGroupPO,Page page);
    
    /**
     * 查询所有可用商品分组列表
     * @param ProductGroupPO
     * @return
     */
    public List<ProductGroupPO> pdtProductGroupAllList();
    
    /**
    * @Title: findByShopId 
    * @Description: 根据店铺ID 获取店铺分组列表
    * @param @param shopId
    * @param @return    参数
    * @return  List<ProductGroupPO>  返回类型
    * @throws
     */
    public List<ProductGroupPO> findByShopId(Integer shopId);

    /**
    * @Description: 新增分组
    * @param @param p
    * @param @return    参数
    * @return  int  返回类型
    * @throws
     */
    int add(ProductGroupPO p);

    /**
    * @Title: delete 
    * @Description: 删除分组
    * @param @param id    参数
    * @return  void  返回类型
    * @throws
     */
    int delete(Integer id);

    /**
     * 更新
    * @Title: update 
    * @param @param group 信息
    * @param @return    参数
    * @return  int  返回类型
    * @throws
     */
    int update(ProductGroupPO group);

    /**
     * 带分组包含的商品数量返回
     * @param page 
    * @param @param shopId
    * @param @return    设定文件
     */
    List<ProductGroupPO> findByShopIdWithCount(int shopId, Page page);

    
}
