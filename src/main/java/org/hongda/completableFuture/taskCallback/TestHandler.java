package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestHandler
 * @Description 某个任务执行完成后，执行的回调方法，有返回值；并且handle方法返回的CompletableFuture的result是第二个任务的结果
 * @Author liuyibo
 * @Date 2024/3/24 14:26
 **/
@Slf4j
public class TestHandler  {
    public static void main(String[] args) {
        defaultHandler();
    }
    public static void defaultHandler(){
        StopWatch stopWatch = new StopWatch("总耗时：");
        stopWatch.start();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });

        CompletableFuture<String> completableFuture = stringCompletableFuture.handle((a, Throwable) -> {
            return a + " world";
        });
       log.info("输出结果为第二个任务：{}",completableFuture.join());
        stopWatch.stop();
        log.info("总耗时：{}",stopWatch.getTotalTimeMillis());
    }
}
