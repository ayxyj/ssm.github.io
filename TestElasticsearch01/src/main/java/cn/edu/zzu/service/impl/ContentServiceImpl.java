package cn.edu.zzu.service.impl;

import cn.edu.zzu.common.elasticsearch.ESCommon;
import cn.edu.zzu.entity.Content;
import cn.edu.zzu.service.IContentService;
import cn.edu.zzu.utils.HtmlParseUtils;
import cn.edu.zzu.utils.JsonUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ContentServiceImpl implements IContentService {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 根据关键词进行解析数据添加到ES中供之后的检索
     * @param keyword
     * @return
     * @throws IOException
     */
    @Override
    public boolean parseAndInsertES(String keyword) throws IOException {
        List<Content> contents = new HtmlParseUtils().parseKey(keyword);
        //批量添加数据
        BulkRequest bulkRequest = new BulkRequest(ESCommon.ES_INDEX.getEs_index());

        //超时配置
        bulkRequest.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //结果封装到请求中
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(new IndexRequest().source(
                    JsonUtil.getJson(contents.get(i)), XContentType.JSON)
            );
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        if (bulk.status() == RestStatus.OK) {
            return true;
        } else {
            return false;
        }
    }
}
