package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class Deptcontroller {

    @Autowired
    RestTemplate restTemplate;

    /*请求路径*/
    //private static final String URL_PATH = "http://localhost:8001";

    //Ribbon实现轮询
    private static final String URL_PATH = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @GetMapping("/get/{id}")
    public Dept findDeptById(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL_PATH+"/dept/get/"+id , Dept.class);
    }

    @PostMapping("/addDept")
    public boolean addDept(Dept dept){
        return restTemplate.postForObject(URL_PATH+"/dept/addDept" , dept , Boolean.class);
    }

    @GetMapping("/findAll")
    public List<Dept> findAll(){
        return restTemplate.getForObject(URL_PATH+"/dept/getAll" ,List.class);
    }
}
