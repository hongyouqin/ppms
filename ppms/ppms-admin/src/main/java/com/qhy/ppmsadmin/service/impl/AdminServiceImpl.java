package com.qhy.ppmsadmin.service.impl;

import java.util.Date;

import com.qhy.ppmsadmin.entity.UserInfo;
import com.qhy.ppmsadmin.common.config.CustomJwtConfig;
import com.qhy.ppmsadmin.dto.UserLoginParam;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.repository.UserInfoRepositoryImpl;
import com.qhy.ppmsadmin.security.JwtTokenUtil;
import com.qhy.ppmsadmin.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserInfoRepositoryImpl userInfoRepository;

    @Autowired
    private CustomJwtConfig config;

    public AdminServiceImpl() {

    }

    /**
     * 查看用户是否已经存在
     */
    public Boolean findUserExist(UserRegisterParam param) {
        UserInfo existUser = userInfoRepository.findByEmail(param.getEmail());
        if (existUser != null) {
            return true;
        }
        existUser = userInfoRepository.findByName(param.getUserName());
        if (existUser != null) {
            return true;
        }
        return false;
    }

    /**
     * 通过email查找用户
     */
    public UserInfo findUser(String email) {
        UserInfo existUser = userInfoRepository.findByEmail(email);
        return existUser;
    }

    @Override
    public UserInfo register(UserRegisterParam param) {
        if (findUserExist(param)) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(param.getEmail());
        userInfo.setUserName(param.getUserName());
        userInfo.setPassword(param.getPassword());
        userInfo.setCreatedTime(new Date());
        return userInfoRepository.save(userInfo);
    }

    @Override
    public String login(UserLoginParam param) {
        UserInfo existUser = findUser(param.getAccount());
        if (existUser == null) {
            return null;
        }
        String pwd = existUser.getPassword();
        if (pwd.equals(param.getPassword())) {
            // 返回jwt token
            JwtTokenUtil jwt = new JwtTokenUtil(config.getSecret(), config.getTokenHead(), config.getExpiration());
            return jwt.generateToken(existUser.getEmail());
        }

        return null;
    }

}
