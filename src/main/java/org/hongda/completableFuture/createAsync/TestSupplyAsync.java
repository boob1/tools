package org.hongda.completableFuture.createAsync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestSupplyAsync
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/23 18:19
 **/
@Slf4j
public class TestSupplyAsync {
    public static void main(String[] args) {
        defaultSupplyAsync();
        customerSupplyAsync();
    }

    public static void defaultSupplyAsync() {
        StopWatch stopWatchAll = new StopWatch("计算总耗时");
        stopWatchAll.start();

        CompletableFuture<Map<String, Object>>  mapAmountCompletableFuture  = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询金额");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("amount", 100);
            stopWatch.stop();
            log.info("{}耗时:{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());
            return map;
        });

        CompletableFuture<HashMap<String, Object>> mapIntegralCompletableFuture = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询积分");
            stopWatch.start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("integral", 100);
            stopWatch.stop();
            log.info("{}耗时:{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());
            return map;
        });

        log.info("查询总金额：{}", mapAmountCompletableFuture.join().get("amount"));
        log.info("查询总积分：{}", mapIntegralCompletableFuture.join().get("integral"));
        stopWatchAll.stop();
        log.info("总耗时:{}...{}", stopWatchAll.getTotalTimeSeconds(), stopWatchAll.getTotalTimeMillis());
    }

    public static void customerSupplyAsync() {
        StopWatch stopWatchAll = new StopWatch("统计总耗时：");
        stopWatchAll.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<HashMap<String, Object>> amountCompletableFuture = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询总金额");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("amount", 100000);
            stopWatch.stop();
            log.info("{}耗时:{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());
            return map;

        }, executorService);

        CompletableFuture<HashMap<String, Object>> pointCompletableFuture = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询总积分");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("point", 100000);
            stopWatch.stop();
            log.info("{}耗时:{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());
            return map;
        }, executorService);

        log.info("查询总金额...{}", amountCompletableFuture.join());
        log.info("查询总积分...{}", pointCompletableFuture.join());
        executorService.shutdown();
        stopWatchAll.stop();
        log.info("总耗时:{}...{}", stopWatchAll.getTotalTimeSeconds(), stopWatchAll.getTotalTimeMillis());
    }
}
