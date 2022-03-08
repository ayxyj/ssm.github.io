package cn.edu.zzu.controller;

import cn.edu.zzu.mapper.EmpMapper;
import cn.edu.zzu.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpMapper empMapper;

    @RequestMapping("/all")
    public List<Emp> getEmp(){
        List<Emp> emps = empMapper.selectAll();
        return emps;
    }
}
