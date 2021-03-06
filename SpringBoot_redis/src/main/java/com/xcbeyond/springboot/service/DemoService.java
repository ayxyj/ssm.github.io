package com.xcbeyond.springboot.service;

import com.xcbeyond.springboot.model.User;

/**
 * Service demo
 */
public interface DemoService {
    /**
     * 通过JdbcTemplate方式插入用户信息
     *
     * @return
     */
    public int insertUserByJdbcTemplate();

    /**
     * 通过userid查询
     *
     * @param userid
     * @return
     */
    public User queryUserByUserid(String userid);

    /**
     * 通过userid更新username
     *
     * @param userid
     * @param username
     */
    public void updateByUserid(String userid, String username);
}
