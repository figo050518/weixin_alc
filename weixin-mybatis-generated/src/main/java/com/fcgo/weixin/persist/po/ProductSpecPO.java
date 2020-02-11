package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class ProductSpecPO {
    /**
     * 主键
     * column: pdt_product_spec.id
     */
    private Integer id;

    /**
     * 商品主表ID
     * column: pdt_product_spec.product_id
     */
    private Integer productId;

    /**
     * 规格名称
     * column: pdt_product_spec.spec_name
     */
    private String specName;

    /**
     * 库存
     * column: pdt_product_spec.stock
     */
    private Integer stock;

    /**
     * 原价
     * column: pdt_product_spec.original_price
     */
    private BigDecimal originalPrice;

    /**
     * 售卖价格
     * column: pdt_product_spec.sales_price
     */
    private BigDecimal salesPrice;

    /**
     * 创建时间
     * column: pdt_product_spec.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * column: pdt_product_spec.update_time
     */
    private Date updateTime;

    /**
     * 添加人
     * column: pdt_product_spec.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: pdt_product_spec.update_name
     */
    private String updateName;

    /**
     * 非常购的规格ID
     * column: pdt_product_spec.fco_spec_id
     */
    private Integer fcoSpecId;

    /**
     * 删除标识
     * column: pdt_product_spec.is_delete
     */
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Integer getFcoSpecId() {
        return fcoSpecId;
    }

    public void setFcoSpecId(Integer fcoSpecId) {
        this.fcoSpecId = fcoSpecId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}