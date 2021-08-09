package cn.edu.zzu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Serializable {
    private String name;
    private String img;
    private String price;
    private String shopnum;
}
