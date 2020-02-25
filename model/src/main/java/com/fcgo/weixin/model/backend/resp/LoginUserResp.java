package com.fcgo.weixin.model.backend.resp;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResp {

    private Integer uid;

    private String userName;

    private Integer brandId;

    private String sessionKey;
}
