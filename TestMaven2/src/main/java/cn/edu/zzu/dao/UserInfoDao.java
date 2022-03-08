package cn.edu.zzu.dao;


import cn.edu.zzu.domain.UserInfo;

import java.util.List;

public interface UserInfoDao {
    public List<UserInfo> findAll() throws Exception;
}
