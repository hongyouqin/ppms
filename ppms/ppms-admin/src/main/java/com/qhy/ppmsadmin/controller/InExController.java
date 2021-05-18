package com.qhy.ppmsadmin.controller;

import com.qhy.ppmsadmin.dto.IncomeRecordParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

//收支管理
@CrossOrigin
@RestController
@Api(tags = "InExController", description = "收支管理页面相关接口")
public class InExController {

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ApiOperation(value = "添加收入")
    @RequestMapping(value = "/income", method = RequestMethod.POST)
    public void addIncome(@RequestBody IncomeRecordParam param) {

    }
}
