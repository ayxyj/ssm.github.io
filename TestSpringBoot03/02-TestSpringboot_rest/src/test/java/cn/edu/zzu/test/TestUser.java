package cn.edu.zzu.test;

import cn.edu.zzu.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TestUser {

    @Test
    public void contextloads(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Result> exchange = testRestTemplate.exchange("http://localhost:8081/user/delete/{id}", HttpMethod.DELETE, null, Result.class, 1);
        System.out.println(exchange.toString());
    }
}
