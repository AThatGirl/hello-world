package com.example.project.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 *
 * @author 杰瑞
 * @date 2022/03/28
 */
public class PasswordUtils {
    /**
     * 加密密码
     *
     * @param password 密码
     * @return {@link String}
     */
    public static String encryptPassword(String password){
        return md5Encrypt(password).substring(8,24);
    }

    /**
     * md5加密
     *
     * @param str 加密的字符串密码
     * @return {@link String}
     */
    public static String md5Encrypt(String str){
        String result=null;
        try {
            //生成加密摘要
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            //计算md5函数
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            result =  new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
