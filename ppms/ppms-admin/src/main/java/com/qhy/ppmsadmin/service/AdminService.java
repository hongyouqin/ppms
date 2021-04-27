package com.qhy.ppmsadmin.service;

import java.util.List;

import com.qhy.ppmsadmin.dto.UserLoginParam;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.entity.UserInfo;

import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {
    /**
     * 注册功能
     */
    UserInfo register(UserRegisterParam param);

    /**
     * 登录功能
     * 
     * @return 生成的JWT的token
     */
    String login(UserLoginParam param);

    /**
     * 通过email查到user
     * 
     * @return 用户详情
     */
    UserDetails loadUserByEmail(String username);

    /**
     * 批量获取用户信息
     * 
     * @return 批量用户信息
     */
    List<UserInfo> userInfoList();
}
