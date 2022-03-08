package cn.edu.zzu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zzu")
public class zzuController {

    /**
     * BeanNameViewResolver会根据handler方法返回的视图名称对应到具体的视图解析
     * @return
     */
    @GetMapping("/test")
    public String order(){
      return "zzu";
    }


    /**
     * 视图解析到静态资源下的html页面
     * @return
     */
    @GetMapping("/html")
    public String gethtml(){
        return "hello";
    }

}
