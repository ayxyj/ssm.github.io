package cn.edu.zzu.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class Result {

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data;

    /**
     * 下列操纵采用链式编程 等同于注解 @Accessors
     */

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result data(Map<String , Object> data){
        this.setData(data);
        return this;
    }

    /**
     * 成功基本数据
     *
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    /**
     * 失败基本数据
     */
    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }
}
