package com.fcgo.weixin.application.impl.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.product.ShopCartService;
import com.fcgo.weixin.application.shop.SellerShopService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.user.UserLoginService;
import com.fcgo.weixin.application.user.UserSessionInfoService;
import com.fcgo.weixin.common.codec.MD5EncrypterUtil;
import com.fcgo.weixin.common.constants.UserType;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.persist.generate.criteria.UserLoginCriteria;
import com.fcgo.weixin.persist.generate.criteria.ValidateCodeLogCriteria;
import com.fcgo.weixin.persist.po.SellerShopPO;
import com.fcgo.weixin.persist.po.UserInfoPO;
import com.fcgo.weixin.persist.po.UserLoginPO;
import com.fcgo.weixin.persist.po.UserSessionInfoPO;
import com.fcgo.weixin.persist.po.ValidateCodeLogPO;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private IUserLoginDAO userLoginDAO;

    @Autowired
    private IUserSessionInfoDAO userSessionInfoDAO;

    @Autowired
    private UserSessionInfoService userSessionInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SellerShopService sellerShopService;

    @Autowired
    private IValidateCodeLogDAO validateCodeLogDAO;

    @Autowired
    private ShopCartService shopCartService;

    @Override
    public boolean addLoginInfo(UserLoginPO userLoginPO) {
        int i = userLoginDAO.insert(userLoginPO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateLogin(UserLoginPO userLoginPO) {
        UserLoginCriteria userLoginCriteria = new UserLoginCriteria();
        userLoginCriteria.createCriteria().andTelephoneEqualTo(userLoginPO.getTelephone())
                .andPasswordEqualTo(MD5EncrypterUtil.md5PwdEncrypt(userLoginPO.getPassword())).andIsDeleteEqualTo(0);
        List<UserLoginPO> userLoginPOs = userLoginDAO.selectByCriteria(userLoginCriteria);
        if (userLoginPOs != null && userLoginPOs.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkSignInPeriod(String signInTelephone, String token, String lastTimeAccessTime) {
        boolean isPeriod = false;
        if (StringUtils.isBlank(lastTimeAccessTime)) {
            return false;
        }
        try {
            long signInPeriodtime = DateUtil.getBJCurrentDate().getTime() - Long.parseLong(lastTimeAccessTime);
            if (signInPeriodtime > (30 * 60L * 1000L)) {
                List<UserSessionInfoPO> sessionInfos =
                        userSessionInfoService.getUsefulByTokenIdAndTepephone(token, signInTelephone);
                if (CollectionUtils.isNotEmpty(sessionInfos)) {
                    UserSessionInfoPO userSessionInfoPO = sessionInfos.get(0);
                    userSessionInfoPO.setDeleteFlag("1");
                    userSessionInfoPO.setDeleteTime(new Date());
                    userSessionInfoDAO.updateByPrimaryKey(userSessionInfoPO);
                }
                isPeriod = true;
            }
        }
        catch (Exception e) {
        }
        return isPeriod;
    }

    @Override
    public boolean validateSignInSuccToken(String signInTelephone, String token, String lastTimeAccessTime) {
        boolean isValidSucc = false;
        if (StringUtils.isBlank(signInTelephone) || StringUtils.isBlank(token)) {
            return false;
        }
        checkSignInPeriod(signInTelephone, token, lastTimeAccessTime);
        // 解密token信息
        List<UserSessionInfoPO> sessionInfos =
                userSessionInfoService.getUsefulByTokenIdAndTepephone(token, signInTelephone);
        if (CollectionUtils.isNotEmpty(sessionInfos)) {
            UserSessionInfoPO sessionInfo = sessionInfos.get(0);
            if (signInTelephone.equals(sessionInfo.getTelephone())) {
                isValidSucc = true;
            }
            else {
                isValidSucc = false;
            }
        }
        else {
            isValidSucc = false;
        }
        return isValidSucc;
    }

    @Override
    public void initSignInFcg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            SignInFcgToken aizhuToken) {
        try {
            // 获取该用户基本信息
            BaseSessionUserDTO baseSessionUserDTO = getUserInfo(aizhuToken);
            // 初始化登录信息
            initSignInInfo(httpServletRequest, httpServletResponse, baseSessionUserDTO);
        }
        catch (Exception e) {
        }

    }

    public void initSignInInfo(HttpServletRequest request, HttpServletResponse response,
            BaseSessionUserDTO baseSessionUserDTO) throws UnsupportedEncodingException {
        HttpSessionProvider.setAttribute(request, response, "session_attr_user", baseSessionUserDTO);
        // 设置session失效时间
        request.getSession().setMaxInactiveInterval(60 * 60 * 24);
        // 添加userName到cookie中
        CookieUtils.addCookie(request, response, CookieUtils.USER_NAME_COOKIE_NAME,
                URLEncoder.encode(baseSessionUserDTO.getNickName(), "UTF-8"), 30 * 24 * 60 * 60, ".fcg.com");
        CookieUtils.addCookie(request, response, CookieUtils.IS_BUYER, baseSessionUserDTO.getIsBuyer().toString(),
                30 * 24 * 60 * 60, ".fcg.com");
        CookieUtils.addCookie(request, response, CookieUtils.IS_SELLER, baseSessionUserDTO.getIsSeller().toString(),
                30 * 24 * 60 * 60, ".fcg.com");
        shopCartService.mergeShopCart(baseSessionUserDTO, response, request);

    }

    @Override
    public BaseSessionUserDTO getUserInfo(SignInFcgToken signInFcgToken) {
        String signInTelephone = signInFcgToken.getSignInTelephone();
        UserInfoPO userInfoPO = userInfoService.getUserInfoByTelephone(signInTelephone);
        int shopId = 0;
        if (userInfoPO != null) {
            int userType = userInfoPO.getUserType();
            Boolean isBuyer = false;
            Boolean isSeller = false;
            if (userType == UserType.BUYER.getKey()) {
                isBuyer = true;
            }
            if (userType == UserType.SELLER.getKey()) {
                // 查询shopID
                SellerShopPO sellerShopPO =
                        sellerShopService.findByParam(null, Integer.valueOf(userInfoPO.getId()).toString());
                if (sellerShopPO != null) {
                    shopId = sellerShopPO.getId();
                }
                isSeller = true;
            }
            BaseSessionUserDTO baseSessionUserDTO =
                    new BaseSessionUserDTO(userInfoPO.getTelNum(), userInfoPO.getNikeName(), isBuyer, isSeller,
                            userInfoPO.getId(), shopId);
            return baseSessionUserDTO;
        }
        return null;
    }

    @Override
    public String forgotPassword(String telephone, String password, String validateCode) {
        // 判断手机号是否存在
        boolean result = userInfoService.isExistTelephone(telephone);
        if (!result) {
            return "errorTelephone";
        }
        // 判断验证码是否正确
        // 判断验证码是否过期
        // 根据手机号码查询最后一个时间发送的验证码
        ValidateCodeLogCriteria validateCodeLogCriteria = new ValidateCodeLogCriteria();
        validateCodeLogCriteria.createCriteria().andTelephoneEqualTo(telephone).andValidateCodeEqualTo(validateCode);
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
        UserLoginCriteria userLoginCriteria = new UserLoginCriteria();
        userLoginCriteria.createCriteria().andTelephoneEqualTo(telephone);
        List<UserLoginPO> userLoginPOs = userLoginDAO.selectByCriteria(userLoginCriteria);
        userLoginPOs.get(0).setPassword(MD5EncrypterUtil.md5PwdEncrypt(password));
        userLoginPOs.get(0).setUpdateTime(new Date());
        // 调用修改
        userLoginDAO.updateByPrimaryKey(userLoginPOs.get(0));
        return "success";
    }
}
