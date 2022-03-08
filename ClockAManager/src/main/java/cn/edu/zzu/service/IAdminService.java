package cn.edu.zzu.service;

import cn.edu.zzu.domain.Admin;
import cn.edu.zzu.domain.User;

public interface IAdminService {
    /**
     * findAdminByName 查询是否存在
     */
    Admin findAdminByName(String username);

    /**
     * findAdminById
     */
    Admin findAdminById(Integer id);
}
