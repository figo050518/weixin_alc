package com.fcgo.weixin.service;

import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.BrandConvert;
import com.fcgo.weixin.convert.UserConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.BrandBo;
import com.fcgo.weixin.model.backend.bo.UserBo;
import com.fcgo.weixin.model.backend.req.UserListReq;
import com.fcgo.weixin.persist.dao.UserMapper;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.persist.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        int total = userMapper.selectCnt();
        if (total==0){
            return pageBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<User> dolist = userMapper.selectAll(offset, pageSize);

        List<UserBo> bos = dolist.stream().map(UserConvert::do2Bo).collect(Collectors.toList());
        int totalPage = PageHelper.getPageTotal(total, pageSize);
        pageBuilder.totalPage(totalPage).list(bos);
        return pageBuilder.build();
    }
}
