package cn.edu.zzu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JacksonUtils {

    public static String getJson(Object object){
        return getJson(object , "yyyy-MM-dd HH:mm:ss");
    }

    private static String getJson(Object object, String dataFormat) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
        objectMapper.setDateFormat(new SimpleDateFormat(dataFormat));
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
