package org.hongda.completableFuture.executeBug1;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName executorServiceTimeOut
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/7 16:36
 **/
public class ExecutorServiceTimeOut {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> taskA = () -> {
            Thread.sleep(3);
            return "A";
        };
        Callable<String> taskB = () -> {
            Thread.sleep(4);
            return "B";
        };

        List<Future<String>> futures = Stream.of(taskA, taskB)
                .map(executorService::submit)
                .collect(Collectors.toList());

        for (Future<String> future : futures) {
            try {
                String s = future.get(2, TimeUnit.MILLISECONDS);
                System.out.println(s);
            } catch (Exception e) {
                System.out.println("Timeout");
                continue;
            }
        }
    }
}
