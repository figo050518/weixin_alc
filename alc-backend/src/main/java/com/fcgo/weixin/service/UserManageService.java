package com.fcgo.weixin.service;

import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.UserConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.UserBo;
import com.fcgo.weixin.model.backend.req.UserListReq;
import com.fcgo.weixin.persist.dao.UserMapper;
import com.fcgo.weixin.persist.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserManageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    public PageResponseBO<UserBo> getList(UserListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();
        PageResponseBO.PageResponseBOBuilder<UserBo> pageBuilder =  PageResponseBO.builder();
        pageBuilder.currentPage(page);
        pageBuilder.pageSize(pageSize);
        String nickName = StringUtils.isBlank(req.getNickName()) ? null : req.getNickName().trim();
        String phone = StringUtils.isBlank(req.getPhone()) ? null : req.getPhone().trim();
        User condition = User.builder().nickName(nickName).phone(phone).build();
        int total = userMapper.selectCnt(condition);
        if (total==0){
            return pageBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<User> dolist = userMapper.selectAll(condition,offset, pageSize);

        List<UserBo> bos = dolist.stream().map(UserConvert::do2Bo).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).total(total).list(bos);
        return pageBuilder.build();
    }


    public Map<Integer,User> getUserMap(Set<Integer> uids){
        List<User> users = userMapper.selectByIds(uids);
        if (CollectionUtils.isEmpty(users)){
            return Collections.emptyMap();
        }
        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
