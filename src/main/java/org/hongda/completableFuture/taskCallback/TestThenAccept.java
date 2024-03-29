package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestThenAccept
 * @Description thenAccept / thenAcceptAsync
 * @Author liuyibo
 * @Date 2024/3/24 9:46
 **/
@Slf4j
public class TestThenAccept {
    public static void main(String[] args) {
        thenAccept();
    }

    public static void thenAccept() {
        StopWatch stopWatchAll = new StopWatch("计算总耗时：");
        stopWatchAll.start();

        CompletableFuture<Map<String, Object>> amountCompletableFuture = CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch("查询总金额");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("amount", 1000);
            stopWatch.stop();

            log.info("{}..{}..{}", stopWatch.getId(), stopWatch.getTotalTimeMillis(), stopWatch.getTotalTimeSeconds());
            return map;
        });

        CompletableFuture<Void> thenCompletableFuture =  amountCompletableFuture.thenAccept((map) -> {
            StopWatch stopWatch = new StopWatch("判断数据");
            stopWatch.start();

            if (Integer.parseInt(map.get("amount").toString()) > 900) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                stopWatch.stop();
                log.info("金额充足可以购买..耗时：{}", stopWatch.getTotalTimeMillis());
            } else {
                stopWatch.stop();
                log.info("不可以购买..{}", stopWatch.getTotalTimeMillis());
            }
        });

        thenCompletableFuture.join();
        stopWatchAll.stop();
        log.info("{}：...{}..{}", stopWatchAll.getId(), stopWatchAll.getTotalTimeSeconds(), stopWatchAll.getTotalTimeMillis());

    }
}
