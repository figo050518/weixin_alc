package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.model.backend.bo.UserBo;
import com.fcgo.weixin.persist.model.User;
import org.springframework.beans.BeanUtils;

public final class UserConvert {

    public static UserBo do2Bo(User user){
        UserBo bo = new UserBo();
        String[] ignoreProps = {"createTime"};
        BeanUtils.copyProperties(user,bo,ignoreProps);
        bo.setCreateTime(DateUtil.format(user.getCreateTime(), DateUtil.Format_yyyy_MM_dd_HH_mm_ss));
        return bo;
    }
}
