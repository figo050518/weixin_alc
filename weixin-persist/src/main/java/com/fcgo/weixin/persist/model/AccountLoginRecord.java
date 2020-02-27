package com.fcgo.weixin.persist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class AccountLoginRecord {
    private Integer uid;

    private String sessionKey;

    private Integer startTime;

    private Integer expiredTime;

    private Integer expired;

}