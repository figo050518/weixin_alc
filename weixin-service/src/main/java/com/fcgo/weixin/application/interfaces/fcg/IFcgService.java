package com.fcgo.weixin.application.interfaces.fcg;

import java.util.List;

import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.fcg.FcgCate;
import com.fcgo.weixin.persist.po.fcg.FcgMerchant;
import com.fcgo.weixin.persist.po.fcg.FcgPostage;
import com.fcgo.weixin.persist.po.fcg.FcgProductFullInfo;
import com.fcgo.weixin.persist.po.fcg.FcgSkuFull;

/**
 * 菲常购对接接口
 * @ClassName: IFcgService 
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月9日 下午11:36:25 
 *
 */
public interface IFcgService {
	
	/**
	 * 查询DDH授予商户的分类列表
	 * @param merchantId	菲常购-商户ID（必填）
	 * @param token			菲常购-token 验证码（必填）
	 * @return 				返回查询到的分类列表,查询失败返回null
	 */
	public List<FcgCate> findFcgCateList(Integer merchantId, String token);
	
	/**
	 * 分页查询菲常购商品列表
	 * @param merchantId	菲常购-商户ID（必填）
	 * @param token			菲常购-token 验证码（必填）
	 * @param fcgCateId		菲常购-分类ID
	 * @param pageNo		分页页码	
	 * @param pageSize		每页记录数		
	 * @param order			排序（desc/asc）
	 * @return 				返回查询到的商品列表,查询失败返回null		
	 */
	public Page findFcgProductListByPage(Integer merchantId, String token, 
			Integer fcgCateId, Integer pageNo, Integer pageSize, String order);
	
	/**
	 * 查询运费信息
	 * @param merchantId	菲常购-商户ID（必填）
	 * @param token			菲常购-token 验证码（必填）
	 * @param areaCode		区代码（必填）
	 * @param skuId			商品SKU ID（必填）
	 * @param count			购买数量（必填）
	 * @return				返回查询到的运费信息
	 */
	public FcgPostage findFcgPostage(Integer merchantId, String token, 
			String areaCode, Integer skuId, Integer count);
	
	/**
	 * 根据非常购商品编号查询商品详情信息
	 * @param merchantId	菲常购-商户ID（必填）
	 * @param token			菲常购-token 验证码（必填）
	 * @param goodsId		非常购商品编号（必填）
	 * @return				返回查询到的商品详情信息,查询失败返回null
	 */
	public FcgProductFullInfo findFcgProductFullInfo(Integer merchantId, String token, Integer goodsId);
	
	/**
	 * 查询菲常购SKU信息
	 * @param merchantId	商户ID（必填）
	 * @param token			token（必填）
	 * @param goodsId		商品ID（必填）
	 * @param skustr		规格信息（必填）
	 * @param areaCode		区代码（必填）
	 * @param count			购买数量（必填）
	 * @return
	 */
	public FcgSkuFull findFcgSkuFull(Integer merchantId, String token, Integer goodsId, String skustr, 
			String areaCode, String count);
	
	/**
	 * 商户登录（授权）接口
	 * @param mobile		手机号
	 * @param password		密码
	 * @return				返回商户授权信息
	 */
	public FcgMerchant doFcgLogin(String mobile, String password);
	
	public boolean doFcgAddOrder();
}
