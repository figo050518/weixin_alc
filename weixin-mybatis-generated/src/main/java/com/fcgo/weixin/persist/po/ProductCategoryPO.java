package com.fcgo.weixin.persist.po;

public class ProductCategoryPO {
    /**
     * 主键
     * column: pdt_category.id
     */
    private Integer id;

    /**
     * 目录名称
     * column: pdt_category.category_name
     */
    private String categoryName;

    /**
     * 删除标识
     * column: pdt_category.is_delete
     */
    private Integer isDelete;

    /**
     * fcg的目录ID
     * column: pdt_category.fcg_category_id
     */
    private Integer fcgCategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getFcgCategoryId() {
        return fcgCategoryId;
    }

    public void setFcgCategoryId(Integer fcgCategoryId) {
        this.fcgCategoryId = fcgCategoryId;
    }
}