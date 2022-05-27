package com.cj.crm.commons.utils;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author 杰瑞
 * @date 2022/05/15
 */
public class UUIDUtil {

    /**
     * 得到uuid的字符串
     *
     * @return {@link String}
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
