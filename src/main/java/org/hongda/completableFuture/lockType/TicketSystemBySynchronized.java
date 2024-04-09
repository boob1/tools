package org.hongda.completableFuture.lockType;

/**
 * @ClassName TicketSystemBySynchronized
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/8 15:12
 **/
public class TicketSystemBySynchronized implements TicketSystem {
    private int ticketNums = 100;

    @Override
    public void sellTicket() {
        while (ticketNums > 0) {
            synchronized (this) {
                try {
                    if (ticketNums > 0) {
                        ticketNums--;
                        System.out.println(Thread.currentThread().getName() + "卖出一张票，还剩余票数" + ticketNums);
                    }
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }
}
