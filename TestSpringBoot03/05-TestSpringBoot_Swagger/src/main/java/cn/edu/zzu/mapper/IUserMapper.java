package cn.edu.zzu.mapper;

import cn.edu.zzu.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMapper {
    List<User> findAllUsers();
}
