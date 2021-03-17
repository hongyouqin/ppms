package com.qhy.ppmsadmin.repository;

import java.util.Date;

import com.qhy.ppmsadmin.dto.UserInfo;

//用户信息接口
public interface UserInfoRepository {
    UserInfo findByName(String userName);

    UserInfo findByEmail(String email);

    UserInfo save(UserInfo user);

    UserInfo updateLoginTime(Date loginTime, String userName);

    UserInfo updatePassword(String password, String userName);
}
