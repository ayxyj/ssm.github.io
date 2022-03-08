package cn.edu.zzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String test(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        return "<h1>Hello 哈哈！</h1>";
    }
    /**
     *    public String test(HttpServletResponse response){
     *         response.setCharacterEncoding("UTF-8");
     *         return "<h1>Hello 哈哈！</h1>";
     *     }
     * */

    @GetMapping("/add/{p1}/{p2}")
    public String add(@PathVariable int p1 , @PathVariable int p2 ){
        int p =  p1+ p2;
        return String.valueOf(p);
    }

    @RequestMapping("/rsm/t1")
    public String test1(){
        //转发  默认转发
        return "index";
    }

    @RequestMapping("/rsm/t2")
    public String test2(){
        //转发二
        return "forward:/index.jsp";
    }

    @RequestMapping("/rsm/t3")
    public String test3(){
        //重定向
        return "redirect:/index.jsp";
    }
}
