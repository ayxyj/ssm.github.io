package cn.edu.zzu;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class TestMd5 {
    @Test
    public void test1() {
        String pwd = "123456";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");// 生成一个MD5加密计算摘要
            md.update(pwd.getBytes());// 计算md5函数
            /**
             * digest()最后确定返回md5 hash值，返回值为8位字符串。
             * 因为md5 hash值是16位的hex值，实际上就是8位的字符
             * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
             * 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
             */
            String hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16是表示转换为16进制数
            System.out.println(hashedPwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMD51() {
        String pwd = "123456";
        // 基于spring框架中的DigestUtils工具类进行密码加密
        String hashedPwd1 = DigestUtils.md5DigestAsHex((pwd).getBytes());
        System.out.println(hashedPwd1);
    }

    @Test
    public  void testMD52() {
        String pwd = "123456";
        String salt = UUID.randomUUID().toString();
        // 基于spring框架中的DigestUtils工具类进行密码加密
        String hashedPwd1 = DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
        System.out.println(hashedPwd1);
    }
}
