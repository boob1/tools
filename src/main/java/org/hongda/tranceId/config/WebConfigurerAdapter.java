package org.hongda.tranceId.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfigurerAdapter
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:28
 **/
@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {


    @Bean
    public  MyIntercepter loginInterceptor(){
        return  new MyIntercepter();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
        // 可以排除不需要拦截
    }


    @Bean("myThreadPoolTaskExecutor")
    public MyThreadPoolTaskExecutor asyncExecutor() {
        MyThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
        // 核心线程数5 线程创建时初始化线程数
        executor.setCorePoolSize(5);
        // 最大线程数5 线程池最大线程数，只有在缓存队列满之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(5);
        // 缓存队列500，用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        // 允许线程的空闲时间60秒，当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀
        executor.setThreadNamePrefix("taiLong_");
        executor.initialize();
        return executor;


    }
}
