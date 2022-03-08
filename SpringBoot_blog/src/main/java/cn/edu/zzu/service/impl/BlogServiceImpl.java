package cn.edu.zzu.service.impl;

import cn.edu.zzu.entity.Blog;
import cn.edu.zzu.mapper.BlogMapper;
import cn.edu.zzu.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
