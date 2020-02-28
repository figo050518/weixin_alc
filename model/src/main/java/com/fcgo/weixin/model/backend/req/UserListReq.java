package com.fcgo.weixin.model.backend.req;

import com.fcgo.weixin.model.PageRequestBO;
import lombok.Data;
import lombok.ToString;

@Data
public class UserListReq extends PageRequestBO {

    private String nickName;

    private String phone;

}
