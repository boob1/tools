package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestThenApply
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/24 10:12
 **/
@Slf4j
public class TestThenApply {
    public static void main(String[] args) {
        defaultThenApply();
    }

    public static void defaultThenApply() {
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

        CompletableFuture<Integer> number = mapCompletableFuture.thenApply((map) -> {
            StopWatch stopWatch = new StopWatch("计算买几瓶饮料：");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return Integer.parseInt(map.get("amount").toString()) / 20;
        });
        log.info("购买可乐的数量：{}", number.join());
        stopWatchAll.stop();
        log.info("总耗时：{}", stopWatchAll.getTotalTimeSeconds());
    }
}
