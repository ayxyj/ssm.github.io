package cn.edu.zzu.controller;

import cn.edu.zzu.entity.Result;
import cn.edu.zzu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class OrderController {

    private final RestTemplate restTemplate;

    public OrderController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @RequestMapping("/order")
    public String order(){
        Result forObject = restTemplate.getForObject("http://localhost:8081/user/{id}", Result.class, 1);
        return forObject.toString();
    }

    @RequestMapping("/add")
    public String add(){
        User user = new User(2  , "zhangsan" ,"河南" ,new Date());
        ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity("http://localhost:8081/user/add", user, Result.class);
        System.out.println(resultResponseEntity.toString());
        return resultResponseEntity.getBody().toString();
    }

    @RequestMapping("/update")
    public String update(){
        User user = new User(1  , "zhangsan" ,"河南");
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);
        ResponseEntity<Result> exchange = restTemplate.exchange("http://localhost:8081/user/update/{id}", HttpMethod.PUT, userHttpEntity, Result.class, 1);
        System.out.println(exchange.toString());
        return exchange.getBody().toString();
    }

    @RequestMapping("/delete")
    public String delete(){
        ResponseEntity<Result> exchange = restTemplate.exchange("http://localhost:8081/user/delete/{id}}", HttpMethod.DELETE, null, Result.class, 1);
        System.out.println(exchange.toString());
        return exchange.getBody().toString();
    }

}
