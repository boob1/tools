package org.hongda.completableFuture.executeBug1;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolExecutorDeadTest
 * @Description execute 提交到线程池的方式，如果执行中抛出异常，并且没有在执行逻辑中catch，那么会抛出异常，并且移除抛出异常的线程，创建新的线程放入到线程池中
 * @Author liuyibo
 * @Date 2024/4/7 15:09
 **/
public class ExecuteTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = buildThreadPoolExecutor();
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute-exception"));
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));

        Thread.sleep(5000);
        System.out.println("再次执行任务.........");

        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));
        executorService.execute(()->exeTask("execute"));
    }

    public static ExecutorService buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(5, 10, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000)
                , new ThreadFactoryBuilder().setNamePrefix("test-%s").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void exeTask(String name) {
        String printStr = "[thread-name:" + Thread.currentThread().getName() + ",执行方法" + name + "]";
        if("execute-exception".equals(name)){
            throw new RuntimeException("我抛异常了");
        }else{
            System.out.println(printStr);
        }
    }
}
