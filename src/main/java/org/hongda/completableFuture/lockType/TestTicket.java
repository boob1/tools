package org.hongda.completableFuture.lockType;

/**
 * @ClassName TestTicket
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/8 15:21
 **/
public class TestTicket {
    public static void main(String[] args) {
        TicketSystem ticketSystem = CodeSandboxFactory.newInstance("Semaphore");
        new Thread(ticketSystem::sellTicket).start();
        new Thread(ticketSystem::sellTicket).start();

    }

    static class CodeSandboxFactory {
        static TicketSystem newInstance(String type) {
            switch (type) {
                case "Synchronized":
                    return new TicketSystemBySynchronized();
                case "ReentrantLock":
                    return new TicketSystemByReentrantLock();
                case "Semaphore":
                default:
                    return new TicketSystemBySemaphore();
            }
        }
    }
}
