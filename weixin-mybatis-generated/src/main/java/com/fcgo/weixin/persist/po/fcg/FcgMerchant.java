package com.fcgo.weixin.persist.po.fcg;
/**
 * 菲常购商户信息
 * @ClassName: FcgMerchant 
 * @Description: TODO
 * @author mail.chenc 
 * @date 2017年4月11日 下午12:53:11 
 *
 */
public class FcgMerchant extends FcgEntity{
	private static final long serialVersionUID = 1L;
	private String f_merchant_address_province = "";		//省代码
	private String f_merchant_fullname = "";				//全名
	private String f_merchant_address = "";					//地址
	private String f_merchant_address_city = "";			//市代码
	private int id = 0;										//id
	private String f_merchant_address_area = "";			//区代码
	private String f_merchant_contactor = "";				//联系人
	private String f_merchant_mobile = "";					//手机号
	private String token = "";								//验证token
	
	public FcgMerchant(){
		super();
	}

	public String getF_merchant_address_province() {
		return f_merchant_address_province;
	}

	public void setF_merchant_address_province(String f_merchant_address_province) {
		this.f_merchant_address_province = f_merchant_address_province;
	}

	public String getF_merchant_fullname() {
		return f_merchant_fullname;
	}

	public void setF_merchant_fullname(String f_merchant_fullname) {
		this.f_merchant_fullname = f_merchant_fullname;
	}

	public String getF_merchant_address() {
		return f_merchant_address;
	}

	public void setF_merchant_address(String f_merchant_address) {
		this.f_merchant_address = f_merchant_address;
	}

	public String getF_merchant_address_city() {
		return f_merchant_address_city;
	}

	public void setF_merchant_address_city(String f_merchant_address_city) {
		this.f_merchant_address_city = f_merchant_address_city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getF_merchant_address_area() {
		return f_merchant_address_area;
	}

	public void setF_merchant_address_area(String f_merchant_address_area) {
		this.f_merchant_address_area = f_merchant_address_area;
	}

	public String getF_merchant_contactor() {
		return f_merchant_contactor;
	}

	public void setF_merchant_contactor(String f_merchant_contactor) {
		this.f_merchant_contactor = f_merchant_contactor;
	}

	public String getF_merchant_mobile() {
		return f_merchant_mobile;
	}

	public void setF_merchant_mobile(String f_merchant_mobile) {
		this.f_merchant_mobile = f_merchant_mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
