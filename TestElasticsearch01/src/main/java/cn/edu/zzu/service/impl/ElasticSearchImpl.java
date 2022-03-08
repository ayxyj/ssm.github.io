package cn.edu.zzu.service.impl;

import cn.edu.zzu.common.elasticsearch.ESCommon;
import cn.edu.zzu.service.IElasticSearchService;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ElasticSearchImpl implements IElasticSearchService {

    //ES 对象
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 实现关键词搜索
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Map<String, Object>> searchKeyword(String keyword, int from, int size) throws IOException {
        //搜索请求对象
        SearchRequest searchRequest = new SearchRequest(ESCommon.ES_INDEX.getEs_index());

        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        //查询条件
        TermQueryBuilder name = QueryBuilders.termQuery("name", keyword);
        searchSourceBuilder.query(name);

        searchRequest.source(searchSourceBuilder);

        //请求查询
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //结果封装
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            maps.add(sourceAsMap);
        }
        //查询结果返回
        return maps;
    }

    /**
     * 高亮
     *
     * @param keyword
     * @param from
     * @param size
     * @return
     * @throws IOException
     */
    @Override
    public List<Map<String, Object>> searchHighLightKeyword(String keyword, int from, int size) throws IOException {
        SearchRequest searchRequest = new SearchRequest(ESCommon.ES_INDEX.getEs_index());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("name");
        searchSourceBuilder.highlighter(highlightBuilder);

        //查询条件
        TermQueryBuilder name = QueryBuilders.termQuery("name", keyword);
        searchSourceBuilder.query(name);

        //封装条件
        searchRequest.source(searchSourceBuilder);

        //请求
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //结果处理
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        if (search.status() == RestStatus.OK) {
            for (SearchHit hit : search.getHits().getHits()) {
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField name1 = highlightFields.get("name");
                System.out.println(name1+"==============");
                String new_name = "";
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                if(name1 !=null){
                    Text[] fragments = name1.fragments();
                    for (Text fragment : fragments) {
                        new_name += fragment;
                    }
                }
                sourceAsMap.put("name", new_name);
                maps.add(sourceAsMap);
            }
            return maps;
        }
        throw new RuntimeException("高亮搜索失败！");
    }
}
