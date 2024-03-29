package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName TestExceptionally
 * @Description exceptionally
 * @Author liuyibo
 * @Date 2024/3/24 10:40
 **/
@Slf4j
public class TestExceptionally {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        defaultThenApply();
    }

    public static void defaultThenApply() throws ExecutionException, InterruptedException {
        StopWatch stopWatchAll = new StopWatch("总耗时");
        stopWatchAll.start();

        CompletableFuture<HashMap<String, Object>> mapCompletableFuture = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询金额");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("amount", 1000);
            stopWatch.stop();
            log.info("{}...{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());
            return map;

        });

        CompletableFuture<Integer> numberCompletableFuture = mapCompletableFuture.thenApply((map) -> {
            StopWatch stopWatch = new StopWatch("计算买几瓶饮料：");
            stopWatch.start();

            try {
                Thread.sleep(1000);
                int i = 1/0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("{}...{}...{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.getTotalTimeMillis());

            return Integer.parseInt(map.get("amount").toString()) / 20;
        });

        CompletableFuture<Integer> exceptionally = numberCompletableFuture.exceptionally((e) -> {
            log.error("发生异常：{}", e.getMessage());
            return 0;
        });
        log.info("购买可乐的数量：{}", numberCompletableFuture.join());
        exceptionally.get();
        stopWatchAll.stop();
        log.info("总耗时：{}", stopWatchAll.getTotalTimeSeconds());
    }
}
