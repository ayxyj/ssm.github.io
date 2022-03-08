package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Dept;
import cn.edu.zzu.service.IDeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class Deptcontroller {

    @Autowired
    IDeptClientService iDeptClientService;

    @GetMapping("/get/{id}")
    public Dept findDeptById(@PathVariable("id") Long id){
        System.out.println(id+"====================");
        return iDeptClientService.findDeptById(id);
    }

    @PostMapping("/addDept")
    public boolean addDept(Dept dept){
        return iDeptClientService.addDept(dept);
    }

    @GetMapping("/findAll")
    public List<Dept> findAll(){
        System.out.println("++++++++++++++++++++++++++++++alll ++++++++++++++++++");
        return iDeptClientService.findAll();
    }
}
