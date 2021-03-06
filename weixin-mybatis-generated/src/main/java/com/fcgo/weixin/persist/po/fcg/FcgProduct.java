package com.fcgo.weixin.persist.po.fcg;

import java.math.BigDecimal;

/**
 * 商品信息
 * @ClassName: FcgProduct 
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月10日 下午8:53:35 
 *
 */
public class FcgProduct extends FcgEntity {
	private static final long serialVersionUID = 1L;
	
	private int f_area_id = 0;
	private int f_goods_scannum = 0;
	private String f_picurl_logo = "";					//图片
	private String f_unit = "";							//单位
	private int f_cate_id = 0;							//分类ID
	private BigDecimal f_price = new BigDecimal(0.00);	//单价
	private int f_brand_id = 0;							//品牌ID
	private int f_goods_virtualnum = 0;
	private int delFlag = 0;							//删除标识
	private String f_created = "";						//创建时间
	private int f_goods_salenum = 0;					//商品销售数量
	private int f_good_tax = 0;
	private String result = "";
	private BigDecimal f_jprice = new BigDecimal(0.00);
	private String f_search_key = "";					//检索关键字
	private String f_spu = "";							//商品SPU码
	private int f_cate_mid = 0;		
	private String f_texes = "";
	private String f_goods_name = "";					//商品名称
	private int f_auth = 0;
	private String property = "";
	private int id = 0;									//商品ID
	private long f_hot = 0;
	private int f_sale = 0;
	
	private String increase ="0"; //增幅
	
	
	public FcgProduct(){
		super();
	}


	public int getF_area_id() {
		return f_area_id;
	}


	public void setF_area_id(int f_area_id) {
		this.f_area_id = f_area_id;
	}


	public int getF_goods_scannum() {
		return f_goods_scannum;
	}


	public void setF_goods_scannum(int f_goods_scannum) {
		this.f_goods_scannum = f_goods_scannum;
	}


	public String getF_picurl_logo() {
		return f_picurl_logo;
	}


	public void setF_picurl_logo(String f_picurl_logo) {
		this.f_picurl_logo = f_picurl_logo;
	}


	public String getF_unit() {
		return f_unit;
	}


	public void setF_unit(String f_unit) {
		this.f_unit = f_unit;
	}


	public int getF_cate_id() {
		return f_cate_id;
	}


	public void setF_cate_id(int f_cate_id) {
		this.f_cate_id = f_cate_id;
	}


	public BigDecimal getF_price() {
		return f_price;
	}


	public void setF_price(BigDecimal f_price) {
		this.f_price = f_price;
	}


	public int getF_brand_id() {
		return f_brand_id;
	}


	public void setF_brand_id(int f_brand_id) {
		this.f_brand_id = f_brand_id;
	}


	public int getF_goods_virtualnum() {
		return f_goods_virtualnum;
	}


	public void setF_goods_virtualnum(int f_goods_virtualnum) {
		this.f_goods_virtualnum = f_goods_virtualnum;
	}


	public int getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}


	public String getF_created() {
		return f_created;
	}


	public void setF_created(String f_created) {
		this.f_created = f_created;
	}


	public int getF_goods_salenum() {
		return f_goods_salenum;
	}


	public void setF_goods_salenum(int f_goods_salenum) {
		this.f_goods_salenum = f_goods_salenum;
	}


	public int getF_good_tax() {
		return f_good_tax;
	}


	public void setF_good_tax(int f_good_tax) {
		this.f_good_tax = f_good_tax;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public BigDecimal getF_jprice() {
		return f_jprice;
	}


	public void setF_jprice(BigDecimal f_jprice) {
		this.f_jprice = f_jprice;
	}


	public String getF_search_key() {
		return f_search_key;
	}


	public void setF_search_key(String f_search_key) {
		this.f_search_key = f_search_key;
	}


	public String getF_spu() {
		return f_spu;
	}


	public void setF_spu(String f_spu) {
		this.f_spu = f_spu;
	}


	public int getF_cate_mid() {
		return f_cate_mid;
	}


	public void setF_cate_mid(int f_cate_mid) {
		this.f_cate_mid = f_cate_mid;
	}


	public String getF_texes() {
		return f_texes;
	}


	public void setF_texes(String f_texes) {
		this.f_texes = f_texes;
	}


	public String getF_goods_name() {
		return f_goods_name;
	}


	public void setF_goods_name(String f_goods_name) {
		this.f_goods_name = f_goods_name;
	}


	public int getF_auth() {
		return f_auth;
	}


	public void setF_auth(int f_auth) {
		this.f_auth = f_auth;
	}


	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getF_hot() {
		return f_hot;
	}


	public void setF_hot(long f_hot) {
		this.f_hot = f_hot;
	}


	public int getF_sale() {
		return f_sale;
	}


	public void setF_sale(int f_sale) {
		this.f_sale = f_sale;
	}

	

	

    public String getIncrease() {
        return increase;
    }


    public void setIncrease(String increase) {
        this.increase = increase;
    }


    public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
