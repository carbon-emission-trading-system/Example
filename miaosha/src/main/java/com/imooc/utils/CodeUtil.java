package com.imooc.utils;

import java.util.Random;

public class CodeUtil {

    //生成随机的6位数字的验证码
    public static String generateCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += String.valueOf(random.nextInt(10));
        }
        return code;
    }

}
