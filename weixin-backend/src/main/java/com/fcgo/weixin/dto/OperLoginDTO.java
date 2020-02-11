package com.fcgo.weixin.dto;

import java.util.Date;

public class OperLoginDTO {

    private String operId;
    private String password;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}