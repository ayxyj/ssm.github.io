package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.utils.JacksonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController  //@Controller+ @ResponseBody  不走视图解析器
public class JacksonController {

    /**
     * JackSon
     * @return
     * @throws JsonProcessingException
     */
    //,produces = "application/json;charset=utf-8"   在配置文件中添加转换器解决所有请求中json乱码问题
    @GetMapping(value = "/j1")
    public String test1() throws JsonProcessingException {
        User test = new User("test哈哈", "123456", new Date());

        String s = JacksonUtils.getJson(test);

        User test1 = new User("test哈哈1", "123456", new Date());
        User test2 = new User("test哈哈2", "123456", new Date());
        User test3 = new User("test哈哈3", "123456", new Date());
        User test4 = new User("test哈哈4", "123456", new Date());

        List<User> list = new ArrayList<User>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        list.add(test4);

        String s1 = JacksonUtils.getJson(list);

        return s1;
    }

    /**
     * fastJson
     * @return
     */
    @GetMapping(value = "/j2")
    public String test2()  {
        User test1 = new User("test哈哈1", "123456", new Date());
        User test2 = new User("test哈哈2", "123456", new Date());
        User test3 = new User("test哈哈3", "123456", new Date());
        User test4 = new User("test哈哈4", "123456", new Date());

        List<User> list = new ArrayList<User>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        list.add(test4);

        String s = JSON.toJSONString(list);
        JSONArray objects = JSON.parseArray(s);
        System.out.println(objects);

        System.out.println("*******Java对象 转 JSON字符串*******===================================");
        String str1 = JSON.toJSONString(list);
        System.out.println("JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(test1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);


        System.out.println("\n****** JSON字符串 转 Java对象*******===================================");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******===================================");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(test2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("username"));

        System.out.println("\n****** JSON对象 转 Java对象 ******===================================");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);


        return s;
    }

}
