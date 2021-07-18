package cn.edu.zzu.test;

import cn.edu.zzu.utils.SchnorrSignature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class TestUtils {


    @Test
    public void check() {
        System.out.println("验证签名开始时间：" + LocalDateTime.now());
        //节点数量就是用户数， 10-50
        for (int i = 0; i < 50; i++) {
            SchnorrSignature.checkSign("e:/1.py", "zzu"+i);
        }
        System.out.println("验证签名结束时间：" + LocalDateTime.now());
    }

    @Test
    @After
    public void make() {
        System.out.println("签名开始时间：" + LocalDateTime.now());
        //节点数量就是用户数， 10-50
        for (int i = 0; i < 50; i++) {
            SchnorrSignature.makeSign("e:/1.py", "zzu" + i, "123456");
        }
        System.out.println("签名结束时间：" + LocalDateTime.now());
    }

    @Test
    @Before
    public void init() {
        System.out.println("初始化开始时间：" + LocalDateTime.now());
        SchnorrSignature.initPara(512);
        //节点数量就是用户数， 1-50
        for (int i = 0; i < 50; i++) {
            SchnorrSignature.generateKeyForUser("zzu" + i, "123456");
        }
        System.out.println("初始化结束时间：" + LocalDateTime.now());
    }
}
