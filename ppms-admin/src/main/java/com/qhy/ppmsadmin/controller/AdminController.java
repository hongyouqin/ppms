package com.qhy.ppmsadmin.controller;

import java.util.HashMap;
import java.util.Map;

import com.qhy.ppmsadmin.common.api.CommonResult;
import com.qhy.ppmsadmin.dto.UserInfo;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.service.AdminService;

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
    private AdminService adminServiceImpl;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<Map<String, String>> login() {
        Map<String, String> tokenMap = new HashMap<>();

        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "新用户注册", response = UserInfo.class, consumes = "application/json")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UserInfo> register(@RequestBody UserRegisterParam param) {
        UserInfo userInfo = adminServiceImpl.register(param);
        if (userInfo == null) {
            return CommonResult.failed("用户名或者邮箱名已经存在");
        }
        return CommonResult.success(userInfo);
    }
}
