package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {

    /**
     * String转日期，自定义类型转换器
     *
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println(user);
        return "success";
    }

    /***
     * RequestParam
     * 请求参数key和实体类名称不一致
     */
    @RequestMapping(value = "/printUser", method = RequestMethod.POST)
    public String printUser(@RequestParam(name = "uname", required = false) String username, String sex) {
        System.out.println(username + ":" + sex);
        return "success";
    }

    /**
     * RequestBody
     * 请求体  用于获取请求体内容。 直接使用得到是 key=value&key=value...结构的数据。
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody(required = false) String body) {
        System.out.println(body);
        return "success";
    }

    /**
     * PathVaribale
     * 用于绑定 url 中的占位符。 例如：请求 url 中 /delete/{id}， 这个{id}就是 url 占位符。
     * url 支持占位符是 spring3.0 之后加入的。是 springmvc 支持 rest 风格 URL 的一个重要标志。
     */

    @RequestMapping("/testPathVaribale/{id}")
    public String testPathVaribale(@PathVariable("id") Integer id) {
        System.out.println(id);
        return "success";
    }

    /**
     * 获取请求头信息
     *
     * @param header
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept", required = false) String header) {
        System.out.println(header);
        return "success";
    }

    /**
     * CookieValue
     * 用于把指定 cookie 名称的值传入控制器方法参数。
     */

    @RequestMapping("/testCookieVale")
    public String testCookieValue(@CookieValue(value = "JSESSIONID", required = false) String cookie) {
        System.out.println(cookie);
        return "success";
    }

    /**
     * 不能覆盖已有的值，只能当参数为null时候进行设置
     *
     * ModelAttribute
     * 该注解是 SpringMVC4.3 版本以后新加入的。它可以用于修饰方法和参数。
     * 出现在方法上，表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可以修饰有具体返回值的方法。
     * 出现在参数上，获取指定的数据给参数赋值。
     *
     * 应用场景：
     * 当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
     * 例如：
     * 我们在编辑一个用户时，用户有一个创建信息字段，该字段的值是不允许被修改的。在提交表单数据是肯定没有此字段的内容，一旦更新会把该字段内容置为 null，此时就可以使用此注解解决问题.

    @ModelAttribute
    public User testModelAttribute(String name ){
        User user = new User();
        user.setSex("男");
        System.out.println("ModeAttribute执行！");
        return user;
    }
     */
    @ModelAttribute
    public void testModelAttribute(String name , Map<String ,User> map){
        User user = new User();
        user.setSex("男");
        user.setDate(new Date());
        System.out.println("ModeAttribute执行！");
        map.put("test" , user);
    }
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("test") User user){
        System.out.println(user);
        return "success";
    }

}
