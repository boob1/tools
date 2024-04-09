package org.hongda.completableFuture.lockType;

import java.util.concurrent.Semaphore;

/**
 * @ClassName TicketSystemBySemaphore
 * @Description Semaphore
 * @Author liuyibo
 * @Date 2024/4/8 15:15
 **/
public class TicketSystemBySemaphore implements TicketSystem{
    private final Semaphore semaphore;

    public TicketSystemBySemaphore() {
        this.semaphore = new Semaphore(100); //总共100张票
    }

    public void sellTicket() {
        int i = semaphore.availablePermits(); //返回此信号量中当前可用的许可证数

        while (i > 0) {
            try {
                Thread.sleep(200);
                semaphore.acquire(); // 获取信号量，如果信号量为0，线程将阻塞等待
                System.out.println(
                        Thread.currentThread().getName() + "卖出一张票，剩余票数：" + --i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release(); // 释放信号量，允许其他线程获取信号量
            }
        }
    }
}
