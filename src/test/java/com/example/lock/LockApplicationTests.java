package com.example.lock;

import com.example.lock.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockApplicationTests {
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public class AnalogUser extends Thread {
        String workerName;//模拟用户姓名
        String openId;
        String openType;
        String amount;
        CountDownLatch latch;

        public AnalogUser(String openId, String openType, String amount,
                          CountDownLatch latch) {
            super();
            this.openId = openId;
            this.openType = openType;
            this.amount = amount;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await(); //一直阻塞当前线程，直到计时器的值为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post();//发送post 请求
        }

        public void post() {
            System.out.println("模拟用户： " + workerName + " 开始发送模拟请求  at " + sdf.format(new Date()));
            String result = HttpUtil.sendGet("http://localhost:8080/walleroptimisticlock", "openId=" + openId + "&openType=" + openType + "&amount=" + amount);
            System.out.println("操作结果：" + result);
            System.out.println("模拟请求结束  at " + sdf.format(new Date()));
        }
    }

    @Test
    public void contextLoads() {
        optimisticLock();
    }

    /**
     * 乐观锁测试
     */
    public void optimisticLock() {
        CountDownLatch latch = new CountDownLatch(1);//模拟5人并发请求，用户钱包

        for (int i = 0; i < 5; i++) {//模拟5个用户
            AnalogUser analogUser = new AnalogUser("58899dcd-46b0-4b16-82df-bdfd0d953bfb", "1", "20.024", latch);
            analogUser.start();
        }
        latch.countDown();//计数器減一  所有线程释放 并发访问。
        System.out.println("所有模拟请求结束  at " + sdf.format(new Date()));


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
