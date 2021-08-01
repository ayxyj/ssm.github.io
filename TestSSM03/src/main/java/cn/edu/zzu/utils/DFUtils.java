package cn.edu.zzu.utils;

import java.text.SimpleDateFormat;

/**
 * 获取日期格式化对象
 */
public class DFUtils {
    private static SimpleDateFormat simpleDateFormat;

    public  static  SimpleDateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
}
