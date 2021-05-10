package cn.edu.zzu.test;

import cn.edu.zzu.dao.Impl.UserInfoDaoImpl;
import cn.edu.zzu.dao.UserInfoDao;
import cn.edu.zzu.domain.UserInfo;
import org.junit.Test;

import java.util.List;

public class UserInfoTest {
    @Test
    public void findAll(){
        UserInfoDao  userInfoDao = new UserInfoDaoImpl();
        try {
            List<UserInfo> userInfos = userInfoDao.findAll();
            for (UserInfo user:userInfos) {
                System.out.println(user.getId());
                System.out.println(user.getLoginName());
                System.out.println(user.getPassword());
                System.out.println(user.getDate());
                System.out.println(user.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
