package cn.edu.zzu.mapper;

import cn.edu.zzu.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDeptMapper {


    /**
     * 添加部门
     * @param dept
     * @return
     */
    boolean addDept(Dept dept);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Dept findDeptById(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Dept> findAll();
}
