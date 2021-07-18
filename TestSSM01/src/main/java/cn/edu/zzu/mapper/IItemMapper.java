package cn.edu.zzu.mapper;

import cn.edu.zzu.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IItemMapper {
    /**
     * 查询所用项目
     */
     List<Item> findAllItems();

    /**
     * 根据id查询
     */
    List<Item> findById(@Param("id")int id);

    /**
     * 增加一项
     */
     int saveItem(Item item);

    /**
     * 删除一项
     */
     int deleteById(@Param("id") int id);
    /**
     * 更新一项
     */
     int updateItem(Item item);
    /**
     * 根据名称查询
     */
    List<Item> findItemByName(String itemName0);
}
