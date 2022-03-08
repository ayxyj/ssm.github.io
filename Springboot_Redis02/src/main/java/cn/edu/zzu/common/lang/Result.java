package cn.edu.zzu.common.lang;

import lombok.Data;

@Data
public class Result {
    private Integer code; //200 正常
    private String msg;
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {

    }
    /**
     * 成功vo
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return Result.success(200, "操作成功", data);
    }


    /**
     * 失败vo
     * @param msg
     * @return
     */
    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result success(int coude, String msg, Object data) {
        Result result = new Result();
        result.setCode(coude);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(int coude, String msg, Object data) {
        Result result = new Result();
        result.setCode(coude);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
