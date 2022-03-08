package cn.edu.zzu.queryVo;

import cn.edu.zzu.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryVo {
    private User user;
}
