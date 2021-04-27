package com.qhy.ppmsadmin.repository;

import java.util.Date;
import java.util.List;

import com.qhy.ppmsadmin.entity.UserInfo;

//用户信息接口
public interface UserInfoRepository {
    UserInfo findByName(String userName);

    UserInfo findByEmail(String email);

    List<UserInfo> findAll();

    UserInfo save(UserInfo user);

    UserInfo updateLoginTime(Date loginTime, String userName);

    UserInfo updatePassword(String password, String userName);
}
