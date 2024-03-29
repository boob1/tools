package org.hongda.ip;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName IpCheck
 * @Description  国家|区域|省份|城市|ISP，
 * @Author liuyibo
 * @Date 2024/3/27 16:35
 **/
public class IpCheck {
    public static void main(String[] args) throws IOException {
        // 1、创建 searcher 对象 (修改为离线库路径)
        String dbPath = "E:\\ipKu\\ip2region.xdb";
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、查询
        String ip = "192.168.10.121";
        try {
            long sTime = System.nanoTime(); // Happyjava
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        searcher.close();

        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
    }

}
