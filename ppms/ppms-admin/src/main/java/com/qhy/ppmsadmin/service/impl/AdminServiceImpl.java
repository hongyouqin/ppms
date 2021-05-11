package com.qhy.ppmsadmin.service.impl;

import java.util.Date;
import java.util.List;

import com.qhy.ppmsadmin.entity.UserInfo;
import com.qhy.ppmsadmin.dto.AdminUserDetails;
import com.qhy.ppmsadmin.dto.UserLoginParam;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.repository.UserInfoRepositoryImpl;
import com.qhy.ppmsadmin.service.AdminService;
import com.qhy.ppmssecurity.common.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserInfoRepositoryImpl userInfoRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    // @Autowired
    // private CustomJwtConfig config;

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
            // 更新登录时间
            userInfoRepository.updateLoginTime(new Date(), existUser.getEmail());

            // 返回jwt token
            AdminUserDetails adminUserDetails = new AdminUserDetails(existUser);
            return jwtTokenUtil.generateToken(adminUserDetails);
        }

        return null;
    }

    @Override
    public UserDetails loadUserByEmail(String username) {
        UserInfo existUser = findUser(username);
        if (existUser == null) {
            return null;
        }
        AdminUserDetails adminUserDetails = new AdminUserDetails(existUser);
        return adminUserDetails;
    }

    @Override
    public List<UserInfo> userInfoList() {
        List<UserInfo> infos = userInfoRepository.findAll();
        return infos;
    }

    @Override
    public UserInfo addUser(UserRegisterParam param) {
        // TODO Auto-generated method stub
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
