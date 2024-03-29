package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestWhenComplete
 * @Description CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；
 * 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
 * @Author liuyibo
 * @Date 2024/3/24 10:48
 **/
@Slf4j
public class TestWhenComplete {
    public static void main(String[] args) {
        defaltWhenComplete();
    }

    public static void defaltWhenComplete() {
        StopWatch stopWatch = new StopWatch("总耗时：");
        stopWatch.start();

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {

            return "周杰伦";
        });
        CompletableFuture<String> stringCompletableFuture = supplyAsync.whenComplete((a, throwable) -> {
            log.info("周杰伦开演唱会！{}.....{}", a, throwable);
        });
        // 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果
        System.out.println(stringCompletableFuture.join());
        stopWatch.stop();

        log.info("总耗时：{}", stopWatch.getTotalTimeMillis());


    }

}
