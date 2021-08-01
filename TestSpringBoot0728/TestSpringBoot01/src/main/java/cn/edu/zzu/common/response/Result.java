package cn.edu.zzu.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Result {
    private Boolean isSuccess ;
    private Integer code ;
    private String Message ;
    private  Object data ;


    /**
     * 里面的方法都是静态方法的
     * 达到保护我们的属性的作用
     */
    private Result() {
    }

    /**
     * 操作成功
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setIsSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData("");
        return result;
    }

    /**
     * 操作成功
     * @param resultCode
     * @return
     */
    public static Result ok(ResultCode resultCode){
        Result result = new Result();
        result.setIsSuccess(true);
        result.setCode(resultCode.SUCCESS.getCode());
        result.setMessage(resultCode.SUCCESS.getMessage());
        result.setData("");
        return result;
    }

    /**
     * 操作失败
     */
    public static Result error(ResultCode resultCode){
        Result result = new Result();
        result.setIsSuccess(false);
        result.setCode(resultCode.FAILED.getCode());
        result.setMessage(resultCode.FAILED.getMessage());
        result.setData("");
        return result;
    }

    /**
     * 操作失败
     */
    public static Result error(String errorMsg) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(errorMsg);
        result.setData("");
        return result;
    }

    /**
     * 操作失败
     */
    public static Result error() {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(ResultCode.FAILED.getMessage());
        result.setData("");
        return result;
    }


    /**
     * 自定义返回成功与否
     *
     * @param success 是否成功
     * @return Result
     */
    public Result success(Boolean success) {
        this.setIsSuccess(success);
        return this;
    }


    public Result message(String message) {
        this.setMessage(message);
        return this;
    }


    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }


    public Result data(Object obj) {
        this.setData(obj);
        return this;
    }


}
