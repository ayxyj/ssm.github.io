# ElasticSearch

## ElasticSearch 基本命令使用（Kibana/postman）

```json
GET _search
{
  "query": {
    "match_all": {}
  }
}

GET _analyze
{
  "analyzer": "ik_smart",
  "text": "中国共产党，夏玉洁，超级喜欢"
}

GET _analyze
{
  "analyzer": "ik_max_word",
  "text": "超级喜欢狂神说Java"
}

PUT /test1/type1/1
{
  "name":"狂神说",
  "age":"18"
}


PUT /test3
{
  "mappings": {
    "properties": {
      "name":{
        "type": "text"
      },
      "age":{
        "type": "long"
      },
      "birthday":{
        "type": "date"
      }
    }
  }
}

GET /test3

PUT /test3/_doc/1
{
  "name":"狂神说",
  "age":18,
  "birthday":"1997-06-06"
}

GET test3

GET _cat/health
GET _cat/indices

# 修改
PUT /test3/_doc/1
{
  "name":"狂神说123",
  "age":18,
  "birthday":"1997-06-06"
}

POST /test3/_doc/1/_update
{
  "doc":{
    "name":"法外狂徒罗翔"
  }
}

DELETE /test2


#==============
PUT /zzu/user/1
{
  "name":"狂神说",
  "age":23,
   "desc":"一顿操作猛如虎",
   "tags":["直男","技术宅"]
}
PUT /zzu/user/2
{
  "name":"罗翔",
  "age":22,
   "desc":"一顿操作猛如虎",
   "tags":["渣男","交友"]
}

PUT /zzu/user/3
{
  "name":"罗1123翔",
  "age":22,
   "desc":"一顿操作猛如虎",
   "tags":["渣男123123","交友2113213"]
}

PUT /zzu/user/4
{
  "name":"罗翔1231231456",
  "age":22,
   "desc":"一顿操作猛如虎",
   "tags":["渣123123男","交友1231"]
}

PUT /zzu/user/5
{
  "name":"罗三",
  "age":22,
   "desc":"一顿操1231作猛如虎",
   "tags":["渣1231416男","交123123友"]
}
PUT /zzu/user/6
{
  "name":"罗通",
  "age":2,
   "desc":"一顿操作猛如虎,一看工资2500",
   "tags":["渣1231416男","交123123友"]
}

GET /zzu/user/2

GET /zzu/user/_search?q=name:罗翔

GET /zzu/user/_search?q=name:狂


GET /zzu/user/_search
{
  "query":{
    "match":{
      "name":"罗"
    }
  }
}

GET /zzu/user/_search
{
  "query":{
    "match":{
      "name":"罗"
    }
  },
  "_source": ["name","desc"]
}


GET /zzu/user/_search
{
  "query":{
    "match":{
      "name":"罗"
    }
  },
    "sort": [
    {
      "age": {
        "order": "asc"
      }
    }
  ],
  "from": 0,
  "size": 2
}

#must ==  and
GET /zzu/user/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "name": "罗"
          }
        },{
          "match": {
            "age": 2
          }
        }
      ]
    }
  }
}

# should == or
GET /zzu/user/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "name": "罗"
          }
        },{
          "match": {
            "age": 2
          }
        }
      ]
    }
  }
}


#must_not == not 

GET /zzu/user/_search
{
  "query": {
    "bool": {
      "must_not": [
       {
          "match": {
            "age": 2
          }
        }
      ]
    }
  }
}

#  >  gt   |    >=  gte    |    <  lt     |     <=   lte  
GET /zzu/user/_search
{
  "query": {
    "bool": {
      "must_not": [
       {
          "match": {
            "age": 2
          }
        }
      ],
      "filter": {
        "range": {
          "age": {
            "gte": 10,
            "lte": 30
          }
        }
      }
    }
  }
}


# 多个查询条件 空格隔开即可

GET /zzu/user/_search
{
  "query": {
    "match": {
      "tags": "男  渣"
    }
  }
}


# term 查询 采用倒排索引进行精确查找

#term 直接查询精确地
#match  会使用分词器解析

PUT testdb
{
  "mappings": {
    "properties": {
      "name":{
        "type": "text"
      },
      "desc":{
        "type": "keyword"
      }
    }
  }
}

GET testdb


PUT /testdb/_doc/1
{
  "name":"狂神说Java name","desc":"狂神说Java desc"
}
PUT /testdb/_doc/2
{
  "name":"狂神说Java name","desc":"狂神说Java desc2"
}


# text  
# keyword

GET _analyze
{
  "analyzer": "keyword",
  "text": "狂神说Java name"
}

GET _analyze
{
  "analyzer": "standard",
  "text": "狂神说Java name"
}

GET testdb/_search
{
  "query": {
    "term": {
     "name": "狂"
    }
  }
}

GET testdb/_search
{
  "query": {
    "term": {
     "desc": "狂神说Java desc"
    }
  }
}

#精确查询
PUT testdb/_doc/4
{
  "t1":"22",
  "t2":"2021-06-08"
}
PUT testdb/_doc/3
{
  "t1":"33",
  "t2":"2021-08-08"
}

GET testdb/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "term": {
              "t1": "22"
            }
          },
          {
             "term": {
              "t1": "33"
             }
          }
      ]
    }
  }
}
GET testdb/_search
{
  "from": 0,
  "size": 20
}


GET zzu/user/_search
{
"query": {
  "match": {
    "name": "狂神"
  }
},
  "highlight": {
    "fields": {
      "name":{
        
      }
    }
  }
}
```

## Elasticsearch 常见api使用

```java
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
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
    //测试索引创建
    public void testCreatedIndex() {
        //创建索引请求对象
        CreateIndexRequest testdb2 = new CreateIndexRequest(ESCommon.ES_INDEX.getEs_index());

        //客户端执行请求，indicesClient 请求后获得相应
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(testdb2, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(createIndexResponse);

    }

    /**
     * 获取索引
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
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest testdb2 = new DeleteIndexRequest(ESCommon.ES_INDEX.getEs_index());
        AcknowledgedResponse delete = client.indices().delete(testdb2, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    /**
     * 测试文档
     */
    @Test
    public void addDoc() throws IOException{


        //创建对象
        User user = new User("狂神说", 30);
        //创建请求
        IndexRequest indexRequest = new IndexRequest(ESCommon.ES_INDEX.getEs_index());
        //规则： PUT /testdb2/_doc/1
        indexRequest.id("1");
        indexRequest.timeout("1s");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));

        //将我们的数据放入请求
        indexRequest.source(JsonUtil.getJson(user), XContentType.JSON);

        //客户端发送请求，获取相应结果
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
        System.out.println("status : "+index.status());
    }

    /**
     * 是否存在doc
     */
    @Test
    public void existDoc() throws IOException {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        //不获取返回的_source上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取doc
     */
    @Test
    public  void  getDoc() throws Exception {
        GetRequest request = new GetRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> map = documentFields.getSourceAsMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key："+key + "  value："+ value);
        }

        System.out.println(documentFields.getSourceAsString());
        System.out.println(documentFields);
    }
    /**
     * 更新doc
     */
    @Test
    public void  updateDoc() throws  Exception{
        UpdateRequest request = new UpdateRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        request.timeout("1s");
        User user = new User("狂神说Java", 18);
        request.doc(JsonUtil.getJson(user), XContentType.JSON);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    /**
     * 删除doc
     */
    @Test
    public void delteDoc() throws Exception{
        DeleteRequest request = new DeleteRequest(ESCommon.ES_INDEX.getEs_index(), "1");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
        System.out.println(delete);
    }

    /**
     * 批处理doc
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
     * 查询
     * SearchRequest搜索请求
     * searchsourceBuilder条件构造
     * HighLightBuiLder构建高亮
     * TermQueryBuiLder精确查询
     * MatchALLQueryBuiLder
     * xxx QueryBuilder
     */
    @Test
    public  void search() throws Exception{
        //查询请求
        SearchRequest searchRequest = new SearchRequest(ESCommon.ES_INDEX.getEs_index());
        //构建搜索
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //高亮
        HighlightBuilder highlighter = searchSourceBuilder.highlighter();

        //查询条件
          //TermQueryBuilder 精确查询 等同于 term
          //MatchAllQueryBuilder 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "test");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60 , TimeUnit.SECONDS));

        //请求封装搜索条件
        searchRequest.source(searchSourceBuilder);

        //请求
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        //结果处理
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

```

