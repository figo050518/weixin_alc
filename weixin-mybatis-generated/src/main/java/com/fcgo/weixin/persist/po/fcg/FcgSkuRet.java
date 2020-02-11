package com.fcgo.weixin.persist.po.fcg;

/**
 * 非常购返回的SKU RET
 * @ClassName: FcgSkuRec 
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月12日 下午10:42:48 
 *
 */
public class FcgSkuRet extends FcgEntity {
	private static final long serialVersionUID = 1L;
	
	private int better = 0;
	private FcgSkuBest bestSku = new FcgSkuBest();
	
	public FcgSkuRet(){
		super();
	}

	public int getBetter() {
		return better;
	}

	public void setBetter(int better) {
		this.better = better;
	}

	public FcgSkuBest getBestSku() {
		return bestSku;
	}

	public void setBestSku(FcgSkuBest bestSku) {
		this.bestSku = bestSku;
	}
}
