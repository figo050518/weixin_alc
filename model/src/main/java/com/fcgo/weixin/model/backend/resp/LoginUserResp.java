package com.fcgo.weixin.model.backend.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResp {

    private Integer uid;

    private String userName;

    private Integer brandId;

    private String sessionKey;
}
