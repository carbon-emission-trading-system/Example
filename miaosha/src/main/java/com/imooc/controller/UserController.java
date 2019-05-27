package com.imooc.controller;

import com.alibaba.druid.util.StringUtils;
import com.imooc.controller.viewobject.UserVO;
import com.imooc.error.BusinessException;
import com.imooc.error.EmBusinessError;
import com.imooc.response.CommonReturnType;
import com.imooc.service.UserService;
import com.imooc.service.model.UserModel;
import com.imooc.utils.CodeUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户登录接口
    @PostMapping("/login")
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParamk(name = "password") String password) throws BusinessException {

        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR);
        }

        //校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, DigestUtils.md5Hex(password));

        //将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.success();
    }


    //用户注册接口
    @PostMapping("/register")
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException {

        //验证手机号和对应的otpCode相符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        System.out.println(inSessionOtpCode);
        System.out.println(otpCode);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "短信验证码错误");
        }

        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(DigestUtils.md5Hex(password));
        userService.register(userModel);

        return CommonReturnType.success();
    }

    //用户获取otp短信接口
    @PostMapping("/getotp")
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        //按规则生成OTP验证码
        String otpCode = CodeUtil.generateCode();

        //将OTP验证码同对应用户的手机号关联,使用httpSession绑定他的手机号与otpCode
        httpServletRequest.getSession().setAttribute(telephone, otpCode);


        //将OTP验证码通过短信通道发送给用户,省略
        System.out.println("telephone=" + telephone + " &otpCode=" + otpCode);

        return CommonReturnType.success();
    }

    @RequestMapping("/get")
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);

        //如果获取的用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = covertFromModel(userModel);
        return CommonReturnType.success(userVO);
    }

    private UserVO covertFromModel(UserModel userModel) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);

        return userVO;
    }


}
