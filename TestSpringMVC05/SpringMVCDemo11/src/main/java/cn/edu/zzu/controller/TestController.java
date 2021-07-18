package cn.edu.zzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {

    @GetMapping("/testView")
    public String test(Model model) {
        //封装数据
        model.addAttribute("msg", "hello 哈哈！");
        return "hello";
    }

    @RequestMapping("/result/t1")
    public void test1(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        rsp.getWriter().println("Hello,Spring BY servlet API");
    }


    @GetMapping("/result/t2")
    public void test2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getSession().getId();
        System.out.println(id);
        resp.sendRedirect("/index.jsp");
    }

    @GetMapping("/result/t3")
    public void test3(HttpServletRequest req, HttpServletResponse rsp) throws Exception {
        //转发
        req.setAttribute("msg", "/result/t3");
        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, rsp);
    }
}
