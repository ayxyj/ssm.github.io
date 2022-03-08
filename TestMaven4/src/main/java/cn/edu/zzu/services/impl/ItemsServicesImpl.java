package cn.edu.zzu.services.impl;

import cn.edu.zzu.domain.Items;
import cn.edu.zzu.mapper.ItemsDao;
import cn.edu.zzu.services.ItemsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServicesImpl implements ItemsServices {

    //spring容器注入
    @Autowired
    private ItemsDao itemsDao;

    public Items findById(Integer id) {
        return itemsDao.findById(id);
    }
}
