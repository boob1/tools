package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestAnyOf
 * @Description // 前提任务任意执行完一个，则目标任务执行。其他前提任务则不在执行。
 * 任意一个任务执行完，就执行anyOf返回的CompletableFuture。如果执行的任务异常，
 * anyOf的CompletableFuture，执行get方法，会抛出异常。
 * @Author liuyibo
 * @Date 2024/3/25 9:05
 **/
@Slf4j
public class TestAnyOf {
    public static void main(String[] args) {
        anyOf();
    }
    public static void anyOf() {
        CompletableFuture<Void> first = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("第一个任务执行完成！");
        });
        CompletableFuture<Void> second = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("第二个任务执行完成！");
        });
        CompletableFuture<Object> voidCompletableFuture = CompletableFuture.anyOf(first, second).whenComplete((m, n) -> {
            log.info("第三个任务完成！");
        });
        voidCompletableFuture.join();
    }
}
