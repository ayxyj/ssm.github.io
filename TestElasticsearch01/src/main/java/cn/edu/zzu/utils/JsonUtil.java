package cn.edu.zzu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class JsonUtil {


    public static String getJson(Object data) {
        return getJson(data, "yyyy-MM-dd HH:mm:ss");
    }

    private static String getJson(Object data, String dataFormat) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS , false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        objectMapper.setDateFormat(simpleDateFormat);
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error!";
    }
}
