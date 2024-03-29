package org.hongda.completableFuture.taskCallback;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TestApplyToEither
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/24 18:05
 **/
@Slf4j
public class TestApplyToEither {
    public static void main(String[] args) {
        //applyToEither1();
        applyToEither();
    }

    public static void applyToEither1() {
        log.info("魏凯下班准备回家。。。");
        log.info("魏凯等待2号，4号地铁。。。");
        CompletableFuture<String> busCF = CompletableFuture.supplyAsync(() -> {
            log.info("2号在路上。。。");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            log.info("4号地铁在路上。。。");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "4";
        }), first -> first + "号");
        log.info("魏凯坐上" + busCF.join() + "地铁");
    }

    public static void applyToEither() {
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 7;
        });
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 8;
        }).applyToEither(first, num -> num);
        log.info("最后结果为：" + second.join());
    }
}
