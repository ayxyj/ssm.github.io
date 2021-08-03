package cn.edu.zzu.serrvice;

import cn.edu.zzu.domain.Dept;

import java.util.List;

public interface IDeptService {
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
