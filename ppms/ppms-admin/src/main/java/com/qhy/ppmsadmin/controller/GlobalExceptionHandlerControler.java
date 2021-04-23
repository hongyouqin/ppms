package com.qhy.ppmsadmin.controller;

import com.qhy.ppmsadmin.common.api.CommonResult;
import com.qhy.ppmsadmin.exception.DatabaseManipulationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandlerControler {

    @ExceptionHandler(DatabaseManipulationException.class)
    @ResponseBody
    public CommonResult<String> handlerDbOptException(DatabaseManipulationException ex) {
        return CommonResult.failed(ex.getMessage());
    }

    public CommonResult<String> handlerExceptoin(Exception ex) {
        return CommonResult.failed(ex.getMessage());
    }
}
