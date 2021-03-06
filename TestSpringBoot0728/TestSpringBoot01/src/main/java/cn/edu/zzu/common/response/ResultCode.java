package cn.edu.zzu.common.response;

public enum ResultCode implements CustomizeResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(400, "操作失败");


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
