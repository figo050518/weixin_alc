package com.fcgo.weixin.common.dto;

import org.apache.commons.lang.StringUtils;

/**
 * @author xiahan
 */
public class SignInFcgToken {

    private final String token;

    private final String signInTelephone;

    private final String signInFcgTime;

    public SignInFcgToken(String token, String signInTelephone, String signInFcgTime) {
        this.token = token;
        this.signInTelephone = signInTelephone;
        this.signInFcgTime = signInFcgTime;
    }

    public String getToken() {
        return token;
    }

    public String getSignInTelephone() {
        return signInTelephone;
    }

    public String getSignInFcgTime() {
        return signInFcgTime;
    }

    @Override
    public String toString() {
        return "SignInQBToken [token=" + token + ", signInTelephone=" + signInTelephone + ", signInAizhuTime="
                + signInFcgTime + "]";
    }

    public boolean isAllowSignIn() {
        if (StringUtils.isBlank(signInTelephone) || StringUtils.isBlank(token)) {
            return false;
        }
        return true;
    }

}
