package cn.edu.zzu;

import cn.edu.zzu.common.response.Result;
import org.junit.Test;

public class TestResult {
    @Test
    public void test(){
        System.out.println(Result.ok().getCode());
    }
}
