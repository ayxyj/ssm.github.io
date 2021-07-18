package cn.edu.zzu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JacksonUtils {

    public static String getJson(Object object){
        return getJson(object , "yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object object, String dataFormat) {
        ObjectMapper objectMapper = new ObjectMapper();
        //关闭时间戳
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        //自定义日期格式
        objectMapper.setDateFormat(simpleDateFormat);
        //对象转Json
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null ;
    }

}
