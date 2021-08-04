package cn.edu.zzu.service;

import cn.edu.zzu.domain.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 负载均衡 feign
 * 请求访问服务提供者的接口信息,请求路径要保持和provider的controller中的path一致才可以
 */
@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class) //熔断降级
//fallbackFactory指定降级配置类
@RequestMapping("/dept")
public interface IDeptClientService {
    /**
     * 添加部门
     * @param dept
     * @return
     */

    @PostMapping("/addDept")
    boolean addDept(Dept dept);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    Dept findDeptById(@PathVariable("id") Long id);

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/getAll")
    List<Dept> findAll();
}
