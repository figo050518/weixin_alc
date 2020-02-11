package com.fcgo.weixin.persist.po.fcg;

import java.math.BigDecimal;

/**
 * 运费信息
 * @ClassName: FcgPostage 
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月10日 下午11:20:57 
 *
 */
public class FcgPostage extends FcgEntity{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal postage = new BigDecimal(0.00);		//运费
	private BigDecimal materials = new BigDecimal(0.00);	//包材费
	
	public FcgPostage(){
		super();
	}

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public BigDecimal getMaterials() {
		return materials;
	}

	public void setMaterials(BigDecimal materials) {
		this.materials = materials;
	}
}
