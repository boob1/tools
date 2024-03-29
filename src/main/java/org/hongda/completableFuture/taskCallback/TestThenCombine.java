package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestThenCombine
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/24 15:28
 **/
@Slf4j
public class TestThenCombine {
    public static void main(String[] args) {
        defaultThenCombine();

    }

    public static void defaultThenCombine(){
        StopWatch stopWatch = new StopWatch("计算总耗时");
        stopWatch.start();

        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> {
            return 4;
        });
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 2).thenCombine(first, (a, b) -> a + b);
        log.info("结果:{}",integerCompletableFuture.join());
        stopWatch.stop();
        log.info("计算总耗时:{}",stopWatch.getTotalTimeMillis());
    }
}
