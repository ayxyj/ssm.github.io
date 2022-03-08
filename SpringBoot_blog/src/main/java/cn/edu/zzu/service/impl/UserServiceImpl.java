package cn.edu.zzu.service.impl;

import cn.edu.zzu.entity.User;
import cn.edu.zzu.mapper.UserMapper;
import cn.edu.zzu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzu
 * @since 2021-07-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
