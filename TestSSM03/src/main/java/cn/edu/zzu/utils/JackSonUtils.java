package cn.edu.zzu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JackSonUtils {

    /**
     * 获取带自定义日期格式的json字符串
     * @param object
     * @param dateFormat
     * @return
     */
    private static String getJson(Object object, String dateFormat) {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS ,false);
        om.setDateFormat(DFUtils.getSimpleDateFormat());
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Json格式化失败");
        }
    }
}
