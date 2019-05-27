package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     * @param telephone      用户手机
     * @param encrptPassword 加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;
}
