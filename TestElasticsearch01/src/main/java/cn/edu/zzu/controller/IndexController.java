package cn.edu.zzu.controller;

import cn.edu.zzu.service.IContentService;
import cn.edu.zzu.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    IContentService iContentService;
    @Autowired
    IElasticSearchService iElasticSearchService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping({"/demo"})
    public String demo() {
        return "demo06";
    }


    /**
     * keyword先插入到es中
     *
     * @param keyword
     * @return
     * @throws IOException
     */
    @GetMapping("/insertES/{keyword}")
    public String insertEsKeyword(@PathVariable("keyword") String keyword) throws IOException {
        boolean b = iContentService.parseAndInsertES(keyword);
        return "index";
    }


    /**
     * 查询结果返回
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}/{from}/{size}")
    @ResponseBody
    public List<Map<String ,Object>> searchKeyword(@PathVariable("keyword") String keyword,
                                @PathVariable("from") int from ,
                                @PathVariable("size") int size) throws IOException{
        List<Map<String, Object>> list = iElasticSearchService.searchKeyword(keyword , from , size);

        return list;
    }

    /**
     * 高亮查询
     * @param keyword
     * @return
     * @throws IOException
     */
    @GetMapping("/searchHighLight/{keyword}/{from}/{size}")
    @ResponseBody
    public List<Map<String, Object>> searchHighLightKeyword(@PathVariable("keyword") String keyword,
                                                            @PathVariable("from") int from ,
                                                            @PathVariable("size") int size) throws IOException{
        List<Map<String, Object>> list = iElasticSearchService.searchHighLightKeyword(keyword , from , size);
        return list;
    }
}
