package cn.edu.zzu.dao;


import cn.edu.zzu.domain.Role;

import java.util.List;

public interface IRoleDao {
    //m:n 查询角色对应的应用  roles:Users
    List<Role> findRolesAndUsers();
}
