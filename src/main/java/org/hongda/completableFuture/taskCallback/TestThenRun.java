package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestThenRun
 * @Description thenRun / thenRunAsync
 * @Author liuyibo
 * @Date 2024/3/24 9:26
 **/
@Slf4j
public class TestThenRun {
    public static void main(String[] args) {
        defaultThenRun();
    }

    /**
     * 执行第一个任务后 可以继续执行第二个任务 两个任务之间无传参 无返回值
     */
    public static void defaultThenRun(){
        StopWatch stopWatchAll = new StopWatch("计算总：");
        stopWatchAll.start();

        CompletableFuture amountCompletableFuture = CompletableFuture.runAsync(() -> {
            StopWatch stopWatch = new StopWatch("计算金额");
            stopWatch.start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("计算金额耗时：{}", stopWatch.getTotalTimeSeconds());
        });

        CompletableFuture thenCompletableFuture = amountCompletableFuture.thenRun(() -> {
            StopWatch stopWatch = new StopWatch("计算总积分");
            stopWatch.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            log.info("{}耗时：{}.....{}", stopWatch.getId(), stopWatch.getTotalTimeSeconds(), stopWatch.prettyPrint());
        });

        thenCompletableFuture.join();

        stopWatchAll.stop();
        log.info("计算总耗时：{}", stopWatchAll.getTotalTimeSeconds());

    }
}
