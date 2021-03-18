package com.qhy.ppmsadmin.service;

import com.qhy.ppmsadmin.dto.UserLoginParam;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.entity.UserInfo;

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

}
