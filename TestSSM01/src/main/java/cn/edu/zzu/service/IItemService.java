package cn.edu.zzu.service;

import cn.edu.zzu.entity.Item;

import java.util.List;

public interface IItemService {
    /**
     * 查询所用项目
     */
    List<Item> findAllItems();

    /**
     * 根据id查询
     */
    List<Item> findById(int id);

    /**
     * 增加一项
     */
    int saveItem(Item item);

    /**
     * 删除一项
     */
    int deleteById(int id);

    /**
     * 更新一项
     */
    int updateItem(Item item);

    /**
     *
     * @param queryName
     */
    List<Item> findItemByName(String queryName);
}
