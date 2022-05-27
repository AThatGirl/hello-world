package com.cj.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对Data类型数据进行处理的工具类
 *
 * @author 杰瑞
 * @date 2022/05/12
 */
public class DateUtil {
    /**
     * 对指定的Data对象进行格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String formatDateTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 对指定的Data对象进行格式化 yyyy-MM-dd
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String formatDate(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 对指定的Data对象进行格式化 HH:mm:ss
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String formatTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

}
