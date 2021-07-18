package cn.edu.zzu.controller;

import cn.edu.zzu.entity.Item;
import cn.edu.zzu.service.IItemService;
import cn.edu.zzu.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    //controller 调用service
    @Autowired
//    @Qualifier("itemServiceImpl")
    private IItemService itemServiceImpl;

    /**
     * 查询所有项目
     */
    @GetMapping("/allItems")
    public String list(Model model) {
        List<Item> allItems = itemServiceImpl.findAllItems();
        String json = JacksonUtils.getJson(allItems);
        model.addAttribute("msg", json);
        model.addAttribute("list", allItems);
        return "allItems";
    }

    /**
     * 跳转到增加项目页面
     */
    @GetMapping("/toAddItem")
    public String toAddItem() {
        return "addItem";
    }

    /**
     * 增加项目
     */
    @RequestMapping("/addItem")
    public String addItem(Item item) {
        System.out.println(item);
        int i = itemServiceImpl.saveItem(item);
        return "redirect:/item/allItems";
    }

    /**
     * 根据id删除一个项目
     */
    @RequestMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        int i = itemServiceImpl.deleteById(id);
        return "redirect:/item/allItems";
    }

    /**
     * 跳转更新项目页面
     */
    @RequestMapping("/toUpdateItem")
    public String toUpdateItem(int id, Model model) {
        //回填
        List<Item> byId = itemServiceImpl.findById(id);
        model.addAttribute("Qitem", byId);
        return "updateItem";
    }

    /**
     * 更新项目
     */
    @RequestMapping("/updateItem")
    public String updateItem(Item item) {
        System.out.println(item);
        int i = itemServiceImpl.updateItem(item);
        return "redirect:/item/allItems";
    }

    /**
     * 查询
     */
    @RequestMapping("/queryItemByName")
    public String findItemByName(String queryName, Model model) {
        List<Item> itemByName = itemServiceImpl.findItemByName(queryName);
        if(itemByName.size()==0){
            itemByName = itemServiceImpl.findAllItems();
            model.addAttribute("error" , "未查到");
        }
        model.addAttribute("list",itemByName);
        return "allItems";
    }
}
