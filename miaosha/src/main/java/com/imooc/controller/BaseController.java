package com.imooc.controller;

import com.imooc.error.BusinessException;
import com.imooc.error.EmBusinessError;
import com.imooc.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    
    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex) {

        CommonReturnType commonReturnType = new CommonReturnType();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            commonReturnType.setCode(businessException.getErrCode());
            commonReturnType.setMessage(businessException.getErrMsg());
        } else {
            commonReturnType.setCode(EmBusinessError.UNKNOW_ERROR.getErrCode());
            commonReturnType.setMessage(EmBusinessError.UNKNOW_ERROR.getErrMsg());
        }

        return commonReturnType;
    }

}
