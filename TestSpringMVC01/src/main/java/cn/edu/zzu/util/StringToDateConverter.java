package cn.edu.zzu.util;



import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 把字符串转换日期
 */
public class StringToDateConverter implements Converter<String,Date> {

    /**
     * String source    传入进来字符串
     * @param source
     * @return
     */
    @Override
    public Date convert(String source) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
