package com.qhy.ppmsadmin.service.impl;

import java.util.Date;

import com.qhy.ppmsadmin.dto.UserInfo;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.repository.UserInfoRepositoryImpl;
import com.qhy.ppmsadmin.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserInfoRepositoryImpl userInfoRepository;

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

}
