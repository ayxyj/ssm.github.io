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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestES {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    //??????????????????
    public void testCreatedIndex() {
        //????????????????????????
        CreateIndexRequest testdb2 = new CreateIndexRequest(ESCommon.ES_INDEX.getEs_index());

        //????????????????????????indicesClient ?????????????????????
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(testdb2, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(createIndexResponse.index());

    }

    /**
     * ????????????
     */
    @Test
    public void getIndex() throws IOException {
        GetIndexRequest testdb2 = new GetIndexRequest(ESCommon.ES_INDEX.getEs_index());
        boolean exists = client.indices().exists(
                testdb2, RequestOptions.DEFAULT
        );
        System.out.println(exists);
    }

    /**
     * ????????????
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest testdb2 = new DeleteIndexRequest(ESCommon.ES_INDEX.getEs_index());
        AcknowledgedResponse delete = client.indices().delete(testdb2, RequestOptions.DEFAULT);
        System.out.println(delete);
    }
    /**
     * ???????????????
     *      XXXRequest
     *  RestHighLevelClient.XX()
     *      index()
     *      exist()
     *      get()
     *      update()
     *      delete()
     *      bulk()
     *      search() //??????????????????
     */

    /**
     * ????????????
     */
    @Test
    public void addDoc() throws IOException{

        //????????????
        User user = new User("?????????", 18);
        //????????????
        IndexRequest indexRequest = new IndexRequest(ESCommon.ES_INDEX.getEs_index());
        //????????? PUT /testdb2/_doc/1
        indexRequest.id("1");
        indexRequest.timeout("1s");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));

        //??????????????????????????????
        indexRequest.source(JsonUtil.getJson(user), XContentType.JSON);

        //??????????????????????????????????????????
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
        System.out.println("status : "+index.status());
    }

    /**
     * ????????????doc
     */
    @Test
    public void existDoc() throws IOException {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        //??????????????????_source?????????  ??????????????????
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * ??????doc
     */
    @Test
    public  void  getDoc() throws Exception {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);

        //map??????
        Map<String, Object> map = documentFields.getSourceAsMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key???"+key + "  value???"+ value);
        }

        //???????????????
        System.out.println(documentFields.getSourceAsString());

        //???????????????
        System.out.println(documentFields);
    }
    /**
     * ??????doc
     */
    @Test
    public void  updateDoc() throws  Exception{
        UpdateRequest request = new UpdateRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        request.timeout("1s");
        User user = new User("?????????Java", 18);
        request.doc(JsonUtil.getJson(user), XContentType.JSON);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    /**
     * ??????doc
     */
    @Test
    public void delteDoc() throws Exception{
        DeleteRequest request = new DeleteRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
        System.out.println(delete);
    }

    /**
     * ?????????doc
     */
    @Test
    public void BulkDoc() throws  Exception{
        BulkRequest bulkRequest = new BulkRequest(ESCommon.ES_INDEX.getEs_index());
        bulkRequest.timeout("10s");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("test10001",15));
        users.add(new User("test10002",16));
        users.add(new User("test10003",17));
        users.add(new User("test10004",18));
        users.add(new User("test10005",19));
        users.add(new User("test10006",20));
        for (int i = 0; i < users.size(); i++) {
            IndexRequest request = new IndexRequest()
                    .id("" + (i + 1))
                    .source(JsonUtil.getJson(users.get(i)),XContentType.JSON);
            bulkRequest.add(request);
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    /**
     * ??????
     * SearchRequest????????????
     * searchsourceBuilder????????????
     * HighLightBuiLder????????????
     * TermQueryBuiLder????????????
     * MatchALLQueryBuiLder
     * xxx QueryBuilder
     */
    @Test
    public  void search() throws Exception{
        //????????????
        SearchRequest searchRequest = new SearchRequest(ESCommon.ES_INDEX.getEs_index());

        //????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //??????
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(60 , TimeUnit.SECONDS));



        //??????
        HighlightBuilder highlighterbuilder = new HighlightBuilder();
        highlighterbuilder.preTags("<span style='color:red'>");
        highlighterbuilder.postTags("</span>");
        highlighterbuilder.field("name");
        //highlighterbuilder.requireFieldMatch(false);
        searchSourceBuilder.highlighter(highlighterbuilder);

        //????????????
          //TermQueryBuilder ???????????? ????????? term
          //MatchAllQueryBuilder ????????????
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "java");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(termQueryBuilder);


        //????????????????????????
        searchRequest.source(searchSourceBuilder);

        //??????
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        //??????????????????????????????
        System.out.println(searchRequest);
        System.out.println(searchSourceBuilder);
        System.out.println(highlighterbuilder);


        //??????????????????
        ArrayList<Map<String , Object>> users = new ArrayList<>();
        //??????????????????
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit+"=========================");
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields+"-----------------------------------------");
            HighlightField name = highlightFields.get("name");
            System.out.println(name);
            //???????????????
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap+"++++++++++++++++++++++++++++++++++++++++++++");
            //????????????????????????????????????????????????????????????????????????
            if(name != null){
                Text[] fragments = name.fragments();
                String new_name = "";
                for (Text fragment : fragments) {
                    new_name += fragment;
                }
                System.out.println(new_name);
                //?????????????????????
                sourceAsMap.put("name",new_name);
            }
                users.add(sourceAsMap);
            System.out.println(users.toString());
        }

        //????????????
        for (SearchHit hit : search.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Iterator<Map.Entry<String, Object>> iterator = sourceAsMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Object> next = iterator.next();
                System.out.println(next.getKey() + "  :  "+ next.getValue());
            }
        }
    }
}
