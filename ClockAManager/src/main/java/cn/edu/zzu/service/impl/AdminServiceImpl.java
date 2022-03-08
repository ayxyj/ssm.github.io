package cn.edu.zzu.service.impl;

import cn.edu.zzu.domain.Admin;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.mapper.IAdminMapper;
import cn.edu.zzu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminMapper iAdminMapper;

    @Override
    public Admin findAdminByName(String username) {
        return iAdminMapper.findAdminByName(username);
    }

    @Override
    public Admin findAdminById(Integer id) {
        return iAdminMapper.findAdminById(id);
    }
}
