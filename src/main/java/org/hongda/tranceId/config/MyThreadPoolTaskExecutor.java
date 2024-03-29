package org.hongda.tranceId.config;

import org.hongda.tranceId.util.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @ClassName MyThreadPoolTaskExecutor
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:28
 **/
public  class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {


    @Override
    public void initialize() {
        super.initialize();
    }



    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }


    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }



    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }



}

