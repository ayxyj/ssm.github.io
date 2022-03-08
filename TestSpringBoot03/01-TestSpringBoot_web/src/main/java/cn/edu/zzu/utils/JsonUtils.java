package cn.edu.zzu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtils {

    /**
     * 返回json字符串
     *
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        return getJson(object, "yyy-MM-dd HH:mm:ss");
    }

    /**
     * 自定义日期类型的json字符串
     *
     * @param object
     * @param dateFormat
     * @return
     */
    public static String getJson(Object object, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        //时间格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//        Date date = new Date();
//        simpleDateFormat.format(date);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //自定义时间格式
        mapper.setDateFormat(simpleDateFormat);
        //对象转json
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
