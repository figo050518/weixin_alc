package com.fcgo.weixin.persist.po.fcg;


/**
 * 菲常购商品详情信息
 * @ClassName: FcgProductDetail 
 * @Description: TODO
 * @author mail.chenc 
 * @date 2017年4月11日 下午1:00:59 
 *
 */
public class FcgProductDetail extends FcgEntity {
	private static final long serialVersionUID = 1L;
	private int f_goods_id = 0;						//商品ID
	private String f_html_content = "";				//html
	private int id = 0;
	private String f_goods_picurls = "";			//图片信息
	private String f_introduce = "";				//介绍
	
	public FcgProductDetail(){
		super();
	}

	public int getF_goods_id() {
		return f_goods_id;
	}

	public void setF_goods_id(int f_goods_id) {
		this.f_goods_id = f_goods_id;
	}

	public String getF_html_content() {
		return f_html_content;
	}

	public void setF_html_content(String f_html_content) {
		this.f_html_content = f_html_content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getF_goods_picurls() {
		return f_goods_picurls;
	}

	public void setF_goods_picurls(String f_goods_picurls) {
		this.f_goods_picurls = f_goods_picurls;
	}

	public String getF_introduce() {
		return f_introduce;
	}

	public void setF_introduce(String f_introduce) {
		this.f_introduce = f_introduce;
	}
}
