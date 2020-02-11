package com.fcgo.weixin.persist.po.fcg;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * 菲常购商品详情（全部信息)
 * @ClassName: FcgProductFullInfo 
 * @Description: TODO
 * @author mail.chenc 
 * @date 2017年4月11日 下午1:06:36 
 *
 */
public class FcgProductFullInfo extends FcgEntity {
	private static final long serialVersionUID = 1L;
	private BigDecimal standPrice = new BigDecimal(0.00);		//标准价格
	private BigDecimal minprice = new BigDecimal(0.00);			//最小价格
	private BigDecimal maxprice = new BigDecimal(0.00);			//最大价格
	
	private FcgProductDetail detail = new FcgProductDetail();	//详情
	private FcgProduct goodsinfo = new FcgProduct();			//商品信息
	private List<String> picurls = new LinkedList<String>();	//图片数组
	
	public FcgProductFullInfo(){
		super();
	}

	public BigDecimal getStandPrice() {
		return standPrice;
	}

	public void setStandPrice(BigDecimal standPrice) {
		this.standPrice = standPrice;
	}

	public BigDecimal getMinprice() {
		return minprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}

	public FcgProductDetail getDetail() {
		return detail;
	}

	public void setDetail(FcgProductDetail detail) {
		this.detail = detail;
	}

	public FcgProduct getGoodsinfo() {
		return goodsinfo;
	}

	public void setGoodsinfo(FcgProduct goodsinfo) {
		this.goodsinfo = goodsinfo;
	}

	public List<String> getPicurls() {
		return picurls;
	}

	public void setPicurls(List<String> picurls) {
		this.picurls = picurls;
	}
}
