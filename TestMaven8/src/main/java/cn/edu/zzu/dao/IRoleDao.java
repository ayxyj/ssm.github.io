package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Role;

import java.util.List;

public interface IRoleDao {
    //findAll
    List<Role> findAll();

    //查询角色对应得用户  m:n
    List<Role> findRoleAndUsers();
}
