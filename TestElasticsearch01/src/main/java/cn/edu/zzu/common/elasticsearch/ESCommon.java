package cn.edu.zzu.common.elasticsearch;

/**
 * elasticsearch的常用常量
 */
public enum ESCommon implements CustomizeElasticSearch{
    ES_INDEX("testdb2") ;
    private String es_index ;

    ESCommon(String path) {
        this.es_index = path;
    }

    public String getEs_index() {
        return es_index;
    }

    public void setEs_index(String es_index) {
        this.es_index = es_index;
    }
}
