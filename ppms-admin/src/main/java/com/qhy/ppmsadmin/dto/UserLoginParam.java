package com.qhy.ppmsadmin.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserLoginParam {
    @ApiModelProperty(value = "账号", required = true)
    private String account;
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
