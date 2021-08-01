package cn.edu.zzu.converter;


import cn.edu.zzu.utils.DFUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        //判断
        if (s == null) {
            throw new RuntimeException("请你传入数据");
        }
        try {
            System.out.println("================================================="+s);
            Date date = (Date) DFUtils.getSimpleDateFormat().parseObject(s.replace('T',' '));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("完蛋~数据类型转换出现错误");
        }
    }
}
