package com.fcgo.weixin.persist.po.fcg;

import java.util.LinkedList;
import java.util.List;
/**
 * 非常购返回的SKU全部信息
 * @ClassName: FcgSkuFull 
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月12日 下午10:51:18 
 *
 */
public class FcgSkuFull extends FcgEntity {
	private static final long serialVersionUID = 1L;
	private int choose = 0;	
	private List<FcgProp> sku = new LinkedList<FcgProp>();
	private FcgSkuRet ret = new FcgSkuRet();
	
	public FcgSkuFull(){
		super();
	}
	public int getChoose() {
		return choose;
	}
	public void setChoose(int choose) {
		this.choose = choose;
	}
	public List<FcgProp> getSku() {
		return sku;
	}
	public void setSku(List<FcgProp> sku) {
		this.sku = sku;
	}
	public FcgSkuRet getRet() {
		return ret;
	}
	public void setRet(FcgSkuRet ret) {
		this.ret = ret;
	}
	
}
