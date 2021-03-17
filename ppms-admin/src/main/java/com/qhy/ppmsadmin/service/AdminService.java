package com.qhy.ppmsadmin.service;

import com.qhy.ppmsadmin.dto.UserInfo;
import com.qhy.ppmsadmin.dto.UserRegisterParam;

public interface AdminService {
    /**
     * 注册功能
     */
    UserInfo register(UserRegisterParam param);

    /**
     * 登录功能
     */

}
