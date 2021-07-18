package cn.edu.zzu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 线程休眠Sleep()方法
 *
 * 1. 注意：sleep()方法使用的位置，如果使用在main线程执行代码中，则阻塞的是main线程。如果在其他线程执行的代码中，则阻塞的是执行这些代码的线程
 * 2. 案例: 倒计时功能
 */
public class TestSleep{
    public static void main(String[] args) throws InterruptedException {
        countDown(10000);
    }

    /**
     * 倒计时方法
     *
     * @param mills 倒计时开始的时间距离当前时间多少毫秒
     */
    public static void countDown(long mills) {
        Date endDate = new Date(System.currentTimeMillis() + mills);
        long endTime = endDate.getTime();

        while (true) {
            System.out.println(new SimpleDateFormat("hh:mm:ss").format(endDate));
            //下一秒时间
            endDate = new Date(endDate.getTime() - 1000);
            //休眠一秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (endTime - endDate.getTime() > mills) {
                break;
            }
        }
    }

}