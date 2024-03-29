package org.hongda.completableFuture.createAsync;

import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestRunAsync
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/23 21:48
 **/
@Slf4j
public class TestRunAsync {
    public static void main(String[] args) {
        defaultRunAsync();
        customerRunAsync();
    }

    public static void defaultRunAsync() {
        StopWatch stopWatchAll = new StopWatch("总耗时：");
        stopWatchAll.start();
        CompletableFuture<Void> amountCompletableFuture = CompletableFuture.runAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询金额：");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            stopWatch.stop();
            log.info("{}耗时：{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds());

        });

        CompletableFuture<Void> jiFenCompletableFuture = CompletableFuture.runAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询余额：");
            stopWatch.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("{}耗时：{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
        });

        log.info("查询金额：{}", amountCompletableFuture.join());
        log.info("查询余额：{}", jiFenCompletableFuture.join());
        stopWatchAll.stop();
        log.info("总耗时：{}", stopWatchAll.getTotalTimeSeconds());
    }

    public static void customerRunAsync() {
        StopWatch stopWatchAll = new StopWatch("总耗时：");
        stopWatchAll.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> amountCompletableFuture = CompletableFuture.runAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询金额：");
            stopWatch.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("{}耗时：{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
        }, executorService);


        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询积分：");
            stopWatch.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("{}耗时：{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
        }, executorService);

        log.info("查询金额：{}", amountCompletableFuture.join());
        log.info("查询积分：{}", voidCompletableFuture.join());
        executorService.shutdown();
        stopWatchAll.stop();
        log.info("总耗时：{}", stopWatchAll.getTotalTimeSeconds());
    }
}
