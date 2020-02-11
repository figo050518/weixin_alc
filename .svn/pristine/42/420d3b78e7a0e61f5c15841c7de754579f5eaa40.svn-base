package com.fcgo.weixin.common.constants;

/**
 * @ClassName: ProductSource
 * @Description: 商品来源
 * @author zhonghui.geng
 * @date 2017年4月7日 下午1:47:52
 */
public enum ProductSource {
    SELFSALE("1", "自营"), PLATFORMSALE("2", "平台");

    private ProductSource(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String value;
    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static ProductSource getValue(String key) {
        for (ProductSource orderStateConstants : ProductSource.values()) {
            if (orderStateConstants.key.equals(key)) {
                return orderStateConstants;
            }
        }
        return null;
    }

}
