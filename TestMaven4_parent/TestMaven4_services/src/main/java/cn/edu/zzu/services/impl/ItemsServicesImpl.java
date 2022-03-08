package cn.edu.zzu.services.impl;

import cn.edu.zzu.domain.Items;

import cn.edu.zzu.mapper.ItemsMapper;
import cn.edu.zzu.services.ItemsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServicesImpl implements ItemsServices {

    //spring容器注入
    @Autowired
    private ItemsMapper itemsMapper;

    public Items findById(Integer id) {
        return itemsMapper.findById(id);
    }
}
