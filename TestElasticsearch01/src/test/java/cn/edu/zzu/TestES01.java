package cn.edu.zzu;

import cn.edu.zzu.common.elasticsearch.ESCommon;
import cn.edu.zzu.entity.User;
import cn.edu.zzu.utils.JsonUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestES01 {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 关于索引的操纵：
     *      XXXXIndexRequest
     * RestHighLevelClient.indices().XX() 增删查
     *      indices().create()
     *      indices().exist()
     *      indices().delete()
     */

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void indexCreate() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(ESCommon.ES_INDEX.getEs_index());
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.index());
    }

    /**
     * index是否存在
     *
     * @throws IOException
     */
    @Test
    public void existIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(ESCommon.ES_INDEX.getEs_index());
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(ESCommon.ES_INDEX.getEs_index() + " " + exists + " 存在");
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(ESCommon.ES_INDEX.getEs_index());
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.toString());
    }

    /**
     * 文档操作：
     *      XXXRequest
     *  RestHighLevelClient.XX()
     *      index()
     *      exist()
     *      get()
     *      update()
     *      delete()
     *      bulk()
     *      search() //根据条件查询
     */

    /**
     * 增加文档
     */
    @Test
    public void addDoc() throws IOException {
        IndexRequest indexRequest = new IndexRequest(ESCommon.ES_INDEX.getEs_index());
        User user = new User("扈三娘", 18);
        //source必须四json类型
        indexRequest.source(JsonUtil.getJson(user), XContentType.JSON);

        //设置id，时延
        indexRequest.id("2");
        indexRequest.timeout(new TimeValue(3000, TimeUnit.SECONDS));

        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.getIndex() + " " + index.status());
    }

    // 测试获得文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(),"1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());// 打印文档内容
        System.out.println(request);// 返回的全部内容和命令是一样的
        client.close();
    }

    /**
     * 文档是否存在
     *
     * @throws IOException
     */
    @Test
    public void existDoc() throws IOException {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文档内容
     * @throws Exception
     */
    @Test
    public void getDoc() throws Exception{
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());
        Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
        for (Map.Entry<String, Object> entry : sourceAsMap.entrySet()) {
            System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
        }
    }

    /**
     * 更新doc
     * @throws IOException
     */
    @Test
    public void updateDoc() throws IOException{
        UpdateRequest request = new UpdateRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        request.doc(JsonUtil.getJson(new User("扈三娘",15)) , XContentType.JSON);
        request.timeout("1s");
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.status() +"  "+ update.getGetResult());
    }

    /**
     * 删除doc
     * @throws Exception
     */
    @Test
    public  void deleteDoc() throws Exception{
        DeleteRequest request = new DeleteRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    /**
     * 批量添加doc
     */
    @Test
    public void bulkDoc() throws IOException{
        BulkRequest bulkRequest = new BulkRequest(ESCommon.ES_INDEX.getEs_index());
        List<User> users = new ArrayList<User>();
        users.add(new User("test001",15));
        users.add(new User("test002",16));
        users.add(new User("test003",17));
        users.add(new User("test004",18));
        users.add(new User("test005",19));
        bulkRequest.timeout("10s");
        for (int i = 0; i < users.size(); i++) {
            //此处如果不设置id，es会生成随机的id
            IndexRequest source = new IndexRequest()
                    .id(""+i)
                    .source(JsonUtil.getJson(users.get(i)), XContentType.JSON);
            bulkRequest.add(source);
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }
    /**
     * 带条件搜索
     */

    @Test
    public  void searchDoc() throws IOException{
        SearchRequest searchRequest = new SearchRequest(ESCommon.ES_INDEX.getEs_index());
        //构建搜索
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(60 , TimeUnit.SECONDS));

        //精确查找
        TermsQueryBuilder termsQueryBuilder = new TermsQueryBuilder("name", "test001");
        //全部查询
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        searchSourceBuilder.query(termsQueryBuilder);
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : search.getHits().getHits()) {
            HighlightField name = hit.getHighlightFields().get("name");
            System.out.println(name);
            System.out.println(hit.getSourceAsString());
        }
    }

}