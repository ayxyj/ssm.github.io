package cn.edu.zzu.mapper;

import cn.edu.zzu.pojo.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emp record);

    Emp selectByPrimaryKey(Integer id);

    List<Emp> selectAll();

    int updateByPrimaryKey(Emp record);
}