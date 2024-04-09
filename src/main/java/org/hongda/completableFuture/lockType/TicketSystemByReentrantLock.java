package org.hongda.completableFuture.lockType;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TicketSystemByReentrantLock
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/8 15:15
 **/
public class TicketSystemByReentrantLock implements TicketSystem {
    private int tickets = 100;

    private final ReentrantLock lock = new ReentrantLock(); //定义锁

    @Override
    public void sellTicket() {
        while (tickets > 0) {
            lock.lock();
            try {

                if (tickets > 0) {
                    tickets--;
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，还剩余票数" + tickets);
                }
               int i= 1/0;
                Thread.sleep(100);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + "线程释放锁");
                lock.unlock();
            }
        }

    }
}
