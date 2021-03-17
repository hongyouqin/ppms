package com.qhy.ppmsadmin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qhy.ppmsadmin.common.api.CommonResult;
import com.qhy.ppmsadmin.dto.UserInfo;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.repository.UserInfoRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "AdminController", description = "登录、注册、登出相关的接口")
@RequestMapping(value = "/auth")
public class AdminController {

    @Autowired
    UserInfoRepositoryImpl userInfoRepository;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<Map<String, String>> login() {
        Map<String, String> tokenMap = new HashMap<>();

        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "新用户注册", response = UserInfo.class, consumes = "application/json")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UserInfo> register(@RequestBody UserRegisterParam param) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(param.getEmail());
        userInfo.setUserName(param.getUserName());
        userInfo.setPassword(param.getPassword());
        userInfo.setCreatedTime(new Date());
        userInfoRepository.save(userInfo);
        return CommonResult.success(userInfo);
    }
}
