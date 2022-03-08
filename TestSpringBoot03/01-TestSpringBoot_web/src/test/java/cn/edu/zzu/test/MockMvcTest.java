package cn.edu.zzu.test;

import cn.edu.zzu.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc //如果没有该注解则需要通过代码进行构建mockmvc类
public class MockMvcTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testMockMvcGET(){
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/user/{id}", 1)//发送get请求
                    .accept(MediaType.APPLICATION_JSON_UTF8)//设置相应文本类型
            )
                    //响应断言
                    .andExpect(MockMvcResultMatchers.status().isOk())//断言状态码为200
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("zzu"))
                    .andDo(MockMvcResultHandlers.print());
            //相应断言
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMockMvcPost(){
        try {
            //language=JSON
            //快捷编写  alt+ enter 切换成json ，然后在alt+enter 选择edit json
            String userJson = "{\n" +
                    "  \"id\": 1,\n" +
                    "  \"username\": \"zzu\",\n" +
                    "  \"address\" : \"河南\"\n" +
                    "}";
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/user/add")//发送get请求
                            .contentType(MediaType.APPLICATION_JSON_UTF8)//设置请求文本类型
                            .content(userJson)
                            .accept(MediaType.APPLICATION_JSON_UTF8)//设置响应文本类型
            )
                    //响应断言
                    .andExpect(MockMvcResultMatchers.status().isOk())//断言状态码为200
                    .andDo(MockMvcResultHandlers.print());
            //相应断言
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
