package cn.edu.zzu.metadata;

public class MySQLJdbcService extends AbstractJdbcService {

    public MySQLJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {
        return "com.mysql.jdbc.Driver";
    }

    @Override
    protected String schemaPattern() {
        //mysql的schemaPattern为数据库名称
        getDataSource().setJdbcUrl("jdbc:mysql://localhost:3306/maven_0416?useUnicode=true&characterEncoding=UTF-8");
        String url = getDataSource().getJdbcUrl();

        String database = url.substring(url.indexOf("/", 13) + 1);
        int index = database.indexOf("?");
        if(index > 0){
            database = database.substring(0, index);
        }
        return database;
    }
}