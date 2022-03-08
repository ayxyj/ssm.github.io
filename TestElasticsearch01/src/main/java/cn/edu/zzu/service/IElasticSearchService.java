package cn.edu.zzu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IElasticSearchService {
    /**
     * 根据关键词搜索
     */
    public List<Map<String, Object>> searchKeyword(String keyword , int from , int size) throws IOException;
    /**
     * 高亮搜索
     */
    public List<Map<String , Object>> searchHighLightKeyword(String keyword , int from , int size) throws IOException;
}
