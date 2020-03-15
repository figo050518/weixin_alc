package com.fcgo.weixin.dada.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * DATE: 18/9/3
 *
 * @author: wan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {

    private String appKey;

    private String appSecret;

    private String host;

    private String sourceId;

    public AppConfig(boolean isOnline){
        this.appKey = AppConstant.APP_KEY;
        this.appSecret = AppConstant.APP_SECRET;
        this.host = isOnline ? AppConstant.ONLINE_HOST : AppConstant.QA_HOST;
        this.sourceId = isOnline ? AppConstant.SOURCE_ID : AppConstant.QA_SOURCE_ID;
    }

}
