package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestAllOf
 * @Description 所有任务都执行完成后，才执行 allOf返回的CompletableFuture。如果任意一个任务异常，
 * allOf的CompletableFuture，执行get方法，会抛出异常。
 * 这里第一次执行没有睡眠的话，是可以直接执行第三个任务的。如果有睡眠，则需要手动join启动。
 * @Author liuyibo
 * @Date 2024/3/25 8:56
 **/
@Slf4j
public class TestAllOf {
    public static void main(String[] args) {
        allOf();
    }

    public static void allOf() {
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
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(first, second).whenComplete((m, n) -> {
            log.info("第三个任务完成！");
        });
        voidCompletableFuture.join();
    }
}
