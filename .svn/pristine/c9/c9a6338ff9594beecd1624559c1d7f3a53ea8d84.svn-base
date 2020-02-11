package com.fcgo.weixin.persist.dao;

import java.util.List;
import java.util.Map;


import com.fcgo.weixin.persist.generate.IProductMapper;
import com.fcgo.weixin.persist.po.ProductPO;

public interface IProductDAO extends IProductMapper {
	
	/**
	 * 分页查询商品列表
	 * @param param
	 * @return
	 */
	 public List<ProductPO> pdtProductList(Map param);

	 /**
	  * 批量上下架
	 * @param upState 
	 * @Description: TODO
	 * @param @param lists    设定文件
	  */
    public void batchUpStateUpdate(List<ProductPO> list);

    public int batchDelete(List<ProductPO> list);
}