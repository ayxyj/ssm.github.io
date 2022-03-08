package cn.edu.zzu.service.impl;

import cn.edu.zzu.entity.Item;
import cn.edu.zzu.mapper.IItemMapper;
import cn.edu.zzu.service.IItemService;

import java.util.List;

public class ItemServiceImpl implements IItemService {

    private IItemMapper iItemMapper;

    public void  setItemMapper(IItemMapper iItemMapper) {
        this.iItemMapper = iItemMapper;
    }
    @Override
    public List<Item> findAllItems() {
        List<Item> allItems = iItemMapper.findAllItems();
        return allItems;
    }

    @Override
    public List<Item> findById(int id) {
        return iItemMapper.findById(id);
    }

    @Override
    public int saveItem(Item item) {
        return iItemMapper.saveItem(item);
    }

    @Override
    public int deleteById(int id) {
        return iItemMapper.deleteById(id);
    }

    @Override
    public int updateItem(Item item) {
        return iItemMapper.updateItem(item);
    }

    @Override
    public List<Item> findItemByName(String queryName) {
        List<Item> itemByName = iItemMapper.findItemByName(queryName);
        return itemByName;
    }
}
