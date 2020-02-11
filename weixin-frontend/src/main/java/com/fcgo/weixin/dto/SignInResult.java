package com.fcgo.weixin.dto;

import org.apache.commons.lang.StringUtils;

/**
 * @author xiahan
 */
public class SignInResult {

    private final boolean signInSucc;

    private final String afterSignInURL;

    public SignInResult(boolean signInSucc) {
        this.signInSucc = signInSucc;
        this.afterSignInURL = StringUtils.EMPTY;
    }

    public SignInResult(boolean signInSucc, String afterSignInURL) {
        this.signInSucc = signInSucc;
        this.afterSignInURL = afterSignInURL;
    }

    public boolean isSignInSucc() {
        return signInSucc;
    }

    public String getAfterSignInURL() {
        return afterSignInURL;
    }

}
