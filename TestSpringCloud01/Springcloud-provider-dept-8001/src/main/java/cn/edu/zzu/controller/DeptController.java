package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Dept;
import cn.edu.zzu.serrvice.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private IDeptService iDeptService;

    //获取eureka中的信息
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/addDept")
    public boolean addDept(@RequestBody Dept dept) {
        return iDeptService.addDept(dept);
    }

    @GetMapping("/get/{id}")
    public Dept getDeptById(@PathVariable("id") Long id) {
        return iDeptService.findDeptById(id);
    }

    @GetMapping("/getAll")
    public List<Dept> findAll() {
        return iDeptService.findAll();
    }

    @GetMapping("/discover")
    public Object discover() {
        //获取微服务的列表清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discover==>services" + services);

        //获取一个具体的服务实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        System.out.println("discover==>instances" + instances);
        for (ServiceInstance instance : instances) {
            System.out.println(
                    "Host:" + instance.getHost() +
                            "  Port:" + instance.getPort() +
                            "  ServiceId："+instance.getServiceId()+
                            "  Uri:"+instance.getUri()+
                            "  InstanceId:" + instance.getInstanceId()
            );
        }
        return this.discoveryClient;
    }
}

