package cn.edu.zzu.service;

import cn.edu.zzu.domain.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 服务降级
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public IDeptClientService create(Throwable throwable) {
        return new IDeptClientService() {
            @Override
            @PostMapping("/dept/addDept")
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            @GetMapping("/dept/get/{id}")
            public Dept findDeptById(@PathVariable("id") Long id) {
                return new Dept().setDeptno(id)
                        .setDname("id==>"+id+"没有对应信息，客户端提供了降级信息，这个服务现在已经被关闭了，~ Null-@HyStrix")
                        .setDb_source("没有数据~!");
            }

            @Override
            @GetMapping("/dept/findAll")
            public List<Dept> findAll() {
                return null;
            }
        };
    }
}
