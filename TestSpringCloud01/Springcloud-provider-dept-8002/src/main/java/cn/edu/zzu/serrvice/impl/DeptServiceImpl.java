package cn.edu.zzu.serrvice.impl;

import cn.edu.zzu.domain.Dept;
import cn.edu.zzu.mapper.IDeptMapper;
import cn.edu.zzu.serrvice.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    IDeptMapper iDeptMapper;
    @Override
    public boolean addDept(Dept dept) {
        return iDeptMapper.addDept(dept);
    }

    @Override
    public Dept findDeptById(Long id) {
        return iDeptMapper.findDeptById(id);
    }

    @Override
    public List<Dept> findAll() {
        return iDeptMapper.findAll();
    }
}
