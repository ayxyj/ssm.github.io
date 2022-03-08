package cn.edu.zzu.mapper;

import cn.edu.zzu.domain.Admin;
import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminMapper {
    /**
     * findAdminByName 查询是否存在
     */
    Admin findAdminByName(String username);

    /**
     * findAdminById
     */
    Admin findAdminById(Integer id);
}
