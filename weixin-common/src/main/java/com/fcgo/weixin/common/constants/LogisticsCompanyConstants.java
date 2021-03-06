package com.fcgo.weixin.common.constants;

/**
 * 物流公司编码
 * 
 * @author xiahan
 */
public enum LogisticsCompanyConstants {
    YUAN_TONG("yuantong", "圆通"), SHENG_TONG("shentong", "申通"), GUO_TONG("guotongkuaidi", "国通"), ZHONG_TONG("zhongtong",
            "中通"), YUN_DA("yunda", "韵达"), EMS("ems", "EMS"), TIANTIAN("tiantian", "天天快递"), HUI_TONG("huitongkuaidi",
            "百世汇通"), YOU_ZHENG("youzhengguonei", "中国邮政"), SHUN_FENG("shunfeng", "顺丰");

    private LogisticsCompanyConstants(String key, String value) {
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

    public static LogisticsCompanyConstants getValue(String key) {
        for (LogisticsCompanyConstants logisticsCompanyConstants : LogisticsCompanyConstants.values()) {
            if (logisticsCompanyConstants.key.equals(key)) {
                return logisticsCompanyConstants;
            }
        }
        return null;
    }

}
