package cn.edu.zzu.controller;

import cn.edu.zzu.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * 自定义异常处理类，覆盖springboot中的异常处理类
 */
@Controller
@RequestMapping("/error")
public class CustomErrorController extends AbstractErrorController {


    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(
            produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);

        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, getErrorAttributeOptions()));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        logger.error(model.get("trace").toString());
        return modelAndView != null ? modelAndView : new ModelAndView("error", model);
    }

    /**
     * 处理ajax
     * 修改返回类型： result  并且加上异常日志记录
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Result error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new Result(204 , "No Content");
        } else {
            Map<String, Object> body = this.getErrorAttributes(request,getErrorAttributeOptions());
            String status1 = body.get("status").toString();
            System.out.println(status1);
            String message = body.get("message").toString();
            logger.error(body.get("trace").toString());
            return new Result(Integer.parseInt(status1) , message);
        }
    }
    protected ErrorAttributeOptions getErrorAttributeOptions() {
        ErrorAttributeOptions of = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.EXCEPTION);
        return of;
    }
}
