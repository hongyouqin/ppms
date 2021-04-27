package com.qhy.ppmsadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhy.ppmsadmin.common.api.CommonResult;
import com.qhy.ppmsadmin.common.config.CustomJwtConfig;
import com.qhy.ppmsadmin.dto.UserLoginParam;
import com.qhy.ppmsadmin.dto.UserRegisterParam;
import com.qhy.ppmsadmin.entity.UserInfo;
import com.qhy.ppmsadmin.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "AdminController", description = "登录、注册、登出相关的接口")
// @RequestMapping(value = "/auth")
public class AdminController {

    @Autowired
    private AdminService adminServiceImpl;

    @Autowired
    private CustomJwtConfig config;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public CommonResult<Map<String, String>> login(@RequestBody UserLoginParam param) {
        String token = adminServiceImpl.login(param);
        if (token == null) {
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", config.getTokenHead());
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "新用户注册")
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public CommonResult<UserInfo> register(@RequestBody UserRegisterParam param) {
        UserInfo userInfo = adminServiceImpl.register(param);
        if (userInfo == null) {
            return CommonResult.failed("用户名或者邮箱名已经存在");
        }
        return CommonResult.success(userInfo);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ApiOperation(value = "分页获得用户信息")
    @RequestMapping(value = "sys/users", method = RequestMethod.GET)
    public CommonResult<List<UserInfo>> userInfoList() {
        List<UserInfo> infos = adminServiceImpl.userInfoList();
        return CommonResult.success(infos);
    }

}
