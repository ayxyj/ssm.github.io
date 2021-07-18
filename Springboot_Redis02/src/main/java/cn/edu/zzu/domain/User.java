package cn.edu.zzu.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户实体类")
public class User implements Serializable {
    @ApiModelProperty("序号")
    private Integer id ;
    @ApiModelProperty("UUID")
    private String userId ;
    @ApiModelProperty("用户名")
    private String username ;
    @ApiModelProperty("密码")
    private String password ;
    @ApiModelProperty("性别")
    private String sex ;
    @ApiModelProperty("住址")
    private String address ;
    @ApiModelProperty("出生日期")
    private Date birthday ;
    @ApiModelProperty("创建时间")
    private Date created ;
    @ApiModelProperty("状态")
    private Integer status ;
}
