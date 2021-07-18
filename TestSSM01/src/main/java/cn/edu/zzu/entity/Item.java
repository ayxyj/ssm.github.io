package cn.edu.zzu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id ;
    private String name ;
    private Float price ;
    private String pic ;
    private Date createtime ;
    private String detail ;

}
