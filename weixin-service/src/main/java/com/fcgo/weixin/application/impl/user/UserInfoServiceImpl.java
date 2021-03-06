package com.fcgo.weixin.application.impl.user;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.codec.MD5EncrypterUtil;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.generate.criteria.UserInfoCriteria;
import com.fcgo.weixin.persist.generate.criteria.UserLoginCriteria;
import com.fcgo.weixin.persist.generate.criteria.ValidateCodeLogCriteria;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;
import com.fcgo.weixin.persist.po.ValidateCodeLogPO;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Autowired
    private IUserLoginDAO userLoginDAO;

    @Autowired
    private IValidateCodeLogDAO validateCodeLogDAO;

    @Override
    @Transactional
    public boolean register(UserLoginPO userLoginPO, UserInfoPO userInfoPO) {
        userInfoPO.setCreateName(userLoginPO.getTelephone());
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setUpdateName(userLoginPO.getTelephone());
        userInfoPO.setUpdateTime(new Date());
        userInfoPO.setTelNum(userLoginPO.getTelephone());
        userInfoPO.setIsDelete(0);
        if (StringUtils.isBlank(userInfoPO.getNikeName())) {
            userInfoPO.setNikeName(userLoginPO.getTelephone());
        }
        userInfoDAO.insert(userInfoPO);
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andTelNumEqualTo(userLoginPO.getTelephone());
        userLoginPO.setCreateName(userLoginPO.getTelephone());
        userLoginPO.setCreateTime(new Date());
        userLoginPO.setUpdateName(userLoginPO.getTelephone());
        userLoginPO.setUpdateTime(new Date());
        userLoginPO.setUserId(userInfoDAO.selectByCriteria(userInfoCriteria).get(0).getId());
        userLoginPO.setIsDelete(0);
        userLoginPO.setPassword(MD5EncrypterUtil.md5PwdEncrypt(userLoginPO.getPassword()));
        int i = userLoginDAO.insert(userLoginPO);
        if (i > 0) {
            return true;
        }
        return true;
    }

    @Override
    public String validateTelephone(String telephone, String code) {
        // 判断手机号是否已经被注册
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andTelNumEqualTo(telephone).andIsDeleteEqualTo(0);
        List<UserInfoPO> userInfoPOs = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoPOs != null && userInfoPOs.size() > 0) {
            return "existTelephone";
        }
        // 判断验证码是否过期
        // 根据手机号码查询最后一个时间发送的验证码
        ValidateCodeLogCriteria validateCodeLogCriteria = new ValidateCodeLogCriteria();
        validateCodeLogCriteria.createCriteria().andTelephoneEqualTo(telephone).andValidateCodeEqualTo(code);
        validateCodeLogCriteria.setOrderByClause("create_time desc");
        List<ValidateCodeLogPO> validateCodeLogs = validateCodeLogDAO.selectByCriteria(validateCodeLogCriteria);
        if (validateCodeLogs == null || validateCodeLogs.size() == 0) {
            return "errorMsgCode";
        }
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        if (validateCodeLogs != null && validateCodeLogs.size() > 0) {
            c.setTime(validateCodeLogs.get(0).getSendTime());
        }
        long last = c.getTimeInMillis();
        if ((now - last) > 120000) {
            return "errorMsgCode";
        }
        return "success";
    }

    @Override
    public boolean isExistTelephone(String telephone) {
        // 判断手机号是否已经被注册
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andTelNumEqualTo(telephone).andIsDeleteEqualTo(0);
        List<UserInfoPO> userInfoPOs = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoPOs != null && userInfoPOs.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public UserInfoPO getUserInfoByTelephone(String telephone) {
        // 判断手机号是否已经被注册
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andTelNumEqualTo(telephone).andIsDeleteEqualTo(0);
        List<UserInfoPO> userInfoPOs = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoPOs != null && userInfoPOs.size() > 0) {
            return userInfoPOs.get(0);
        }
        return null;
    }

    @Override
    public UserInfoPO findByFcgId(String fcgId) {
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andFcgSellerIdEqualTo(Integer.valueOf(fcgId));
        List<UserInfoPO> userInfoList = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoList == null || userInfoList.isEmpty() || userInfoList.size() > 1) {
            return null;
        }
        return userInfoList.get(0);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<UserInfoPO> listUsers(UserInfoPO userInfoPO, Page page) {
        // TODO Auto-generated method stub

        // 查询记录
        Map parm = new HashMap();
        if (StringUtils.isNotEmpty(userInfoPO.getNikeName())) {
            parm.put("nike_name", "%" + userInfoPO.getNikeName() + "%");
        }
        parm.put("user_type", userInfoPO.getUserType());
        parm.put("tel_num", userInfoPO.getTelNum());
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        List<UserInfoPO> userInfoPOs = userInfoDAO.lisPagertUserInfos(parm);

        // 查询记录条数
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        if (StringUtils.isNotEmpty(userInfoPO.getNikeName())) {
            userInfoCriteria.createCriteria().andNikeNameLike("%" + userInfoPO.getNikeName() + "%");
        }
        if (userInfoPO.getUserType() != null) {
            userInfoCriteria.createCriteria().andUserTypeEqualTo(userInfoPO.getUserType());
        }
        if (StringUtils.isNotEmpty(userInfoPO.getTelNum())) {
            userInfoCriteria.createCriteria().andTelNumEqualTo(userInfoPO.getTelNum());
        }
        int count = userInfoDAO.countByCriteria(userInfoCriteria);
        page.setRow(userInfoPOs);
        page.setRecords(count);

        return userInfoPOs;
    }

    @Override
    public UserInfoPO findById(int id) {
        return userInfoDAO.selectByPrimaryKey(id);
    }

    @Override
    public boolean userActive(UserInfoPO userInfoPO) {
        int cnt = userInfoDAO.updateByPrimaryKey(userInfoPO);
        return cnt > 0;
    }

    @Override
    public boolean isExistTelephoneByUserId(String telephone, String userId) {
        // 判断手机号是否已经被注册
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andTelNumEqualTo(telephone).andIsDeleteEqualTo(0)
                .andIdEqualTo(Integer.valueOf(userId));
        List<UserInfoPO> userInfoPOs = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoPOs != null && userInfoPOs.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean changeNickName(UserInfoPO userInfoPO) {
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andIdEqualTo(userInfoPO.getId());
        int i = userInfoDAO.updateByCriteriaSelective(userInfoPO, userInfoCriteria);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(UserLoginPO userLoginPO) {
        UserLoginCriteria userLoginCriteria = new UserLoginCriteria();
        userLoginCriteria.createCriteria().andUserIdEqualTo(userLoginPO.getUserId());
        int i = userLoginDAO.updateByCriteriaSelective(userLoginPO, userLoginCriteria);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changeTelephone(UserInfoPO userInfoPO, UserLoginPO userLoginPO) {
        UserLoginCriteria userLoginCriteria = new UserLoginCriteria();
        userLoginCriteria.createCriteria().andUserIdEqualTo(userLoginPO.getUserId());
        int i = userLoginDAO.updateByCriteriaSelective(userLoginPO, userLoginCriteria);
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andIdEqualTo(userInfoPO.getId());
        int j = userInfoDAO.updateByCriteriaSelective(userInfoPO, userInfoCriteria);
        if (i > 0 && j > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String validateMsgCode(String telephone, String code) {
        // 判断验证码是否过期
        // 根据手机号码查询最后一个时间发送的验证码
        ValidateCodeLogCriteria validateCodeLogCriteria = new ValidateCodeLogCriteria();
        validateCodeLogCriteria.createCriteria().andTelephoneEqualTo(telephone).andValidateCodeEqualTo(code);
        validateCodeLogCriteria.setOrderByClause("create_time desc");
        List<ValidateCodeLogPO> validateCodeLogs = validateCodeLogDAO.selectByCriteria(validateCodeLogCriteria);
        if (validateCodeLogs == null || validateCodeLogs.size() == 0) {
            return "errorMsgCode";
        }
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        if (validateCodeLogs != null && validateCodeLogs.size() > 0) {
            c.setTime(validateCodeLogs.get(0).getSendTime());
        }
        long last = c.getTimeInMillis();
        if ((now - last) > 120000) {
            return "errorMsgCode";
        }
        return "success";
    }

    @Override
    public UserInfoPO findByOpenId(String openId) {
        // 判断手机号是否已经被注册
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andWxIdEqualTo(openId).andIsDeleteEqualTo(0);
        List<UserInfoPO> userInfoPOs = userInfoDAO.selectByCriteria(userInfoCriteria);
        if (userInfoPOs != null && userInfoPOs.size() > 0) {
            return userInfoPOs.get(0);
        }
        return null;
    }

}
