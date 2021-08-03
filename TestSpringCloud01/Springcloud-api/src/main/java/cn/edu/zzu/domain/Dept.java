package cn.edu.zzu.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Accessors(chain = true)//链式编程  return this;
public class Dept implements Serializable {
    private Long deptno ; //主键
    private String dname ; //部门

    //微服务 通常一个服务对应一个数据库，同一个信息可能存在不同的数据库中
    private String db_source ; //数据库名称

    public Dept(String dname){
        this.dname = dname ;
    }
}
