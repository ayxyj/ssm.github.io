package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Dept;
import cn.edu.zzu.serrvice.IDeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private IDeptService iDeptService;

    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")  //方法服务熔断
    public Dept getDeptById(@PathVariable("id") Long id) {
        Dept deptById = iDeptService.findDeptById(id);
        if (deptById == null) {
            throw new RuntimeException("id==>"+id+"不存在该用户，或者信息无法找到~");
        }
        return deptById;
    }

    /**
     * 根据id查询备选方案(熔断)
     * @param id
     * @return
     */
    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDeptno(id)
                .setDname("id==>"+id+"不存在该用户，或者信息无法找到~ Null-@HyStrix")
                .setDb_source("no this database in MySQL!");
    }


}

