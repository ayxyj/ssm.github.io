package cn.edu.zzu.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户名")
    private String username ;
    @ApiModelProperty("用户密码")
    private String password ;
    @ApiModelProperty("出生年月")
    private Date   birthday ;
}
