package cn.edu.zzu;

import cn.edu.zzu.entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestMap {

    @Test
    public void testMapTraversal() {
        Map<String, Object> map = new HashMap<>();
        map.put("10001", new User("test001", 16));
        map.put("10002", new User("test002", 16));
        map.put("10003", new User("test003", 16));
        map.put("10004", new User("test004", 16));
        map.put("10005", new User("test005", 16));
        method04(map);
    }

    /**
     * 方式四
     *  使用For-Each迭代keys和values
     * 遍历key
     * 遍历value
     */
    public void method04(Map<String, Object> map){
        for (String key : map.keySet()) {
            System.out.println("key:"+key );
        }
        for (Object value : map.values()) {
            System.out.println("value:"+ value);
        }
    }
    /**
     * 方式三
     * 迭代keys并搜索values（低效的）
     */

    public  void method03(Map<String, Object> map){
        for (String key : map.keySet()) {
            System.out.println("key :"+ key + "value:" +map.get(key));
        }
    }
    /**
     * 方式二
     * 使用For-Each迭代entries
     * 这是最常见的方法，并在大多数情况下更可取的。当你在循环中需要使用Map的键和值时，就可以使用这个方法
     */
    public void method02(Map<String, Object> map) {
        for (Map.Entry<String ,Object> entry  : map.entrySet()  ) {
            System.out.println(entry.getKey() +" : "+ entry.getValue());
        }

    }


    /**
     * 方式一
     * 使用Iterator迭代
     */
    public void method(Map<String, Object> map) {
        //获取map的迭代器
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        //循环遍历
        while(iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey()+" : "+next.getValue());
        }
    }


}
