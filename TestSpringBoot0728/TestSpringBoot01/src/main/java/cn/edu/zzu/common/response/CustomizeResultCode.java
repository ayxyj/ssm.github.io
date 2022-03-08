package cn.edu.zzu.common.response;

public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息；
     */
    String getMessage();
}
