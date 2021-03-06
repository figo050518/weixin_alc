package com.fcgo.weixin.dto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fcgo.weixin.persist.po.ProductImagePO;

public class ProductDTO {

    private Integer id;
    
    private String productName;
    
    private BigDecimal freight;
    
    private int upState;
    
    private ProductGroupDTO group;
    
    private String productDesc;
    
    private Integer shopId;
    
    private Integer fromType;
    
    private Integer fcgProductId;
    
    private Integer fcgProductType;
    
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    
    private List<ProductImagePO> imageList;
    
    private List<ProductSpecDTO> sepcList;

    private Integer salesCount;
    
    private Integer stocks;
    
    private List<String> imgUrlList;
    
    private Integer fcgCateId;
    
    private String fcgCateName;
    
    
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }


    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUpState() {
        return upState;
    }

    public void setUpState(int upState) {
        this.upState = upState;
    }

    public ProductGroupDTO getGroup() {
        return group;
    }

    public void setGroup(ProductGroupDTO group) {
        this.group = group;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getFcgProductId() {
        return fcgProductId;
    }

    public void setFcgProductId(Integer fcgProductId) {
        this.fcgProductId = fcgProductId;
    }

    
    public List<ProductImagePO> getImageList() {
		return imageList;
	}

	public void setImageList(List<ProductImagePO> imageList) {
		this.imageList = imageList;
	}

	public List<ProductSpecDTO> getSepcList() {
        return sepcList;
    }

    public void setSepcList(List<ProductSpecDTO> sepcList) {
        this.sepcList = sepcList;
    }

    public Integer getFcgProductType() {
        return fcgProductType;
    }

    public void setFcgProductType(Integer fcgProductType) {
        this.fcgProductType = fcgProductType;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

	public Integer getFcgCateId() {
		return fcgCateId;
	}

	public void setFcgCateId(Integer fcgCateId) {
		this.fcgCateId = fcgCateId;
	}

	public String getFcgCateName() {
		return fcgCateName;
	}

	public void setFcgCateName(String fcgCateName) {
		this.fcgCateName = fcgCateName;
	}
    
    
    
    

}
