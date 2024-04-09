package org.hongda.page.page1.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description 配置并启用MyBatis-Plus的分页插件
 * @Author liuyibo
 * @Date 2024/4/9 18:29
 **/
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // MybatisPlusInterceptor是MyBatis-Plus中用于配置拦截器的类
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // PaginationInnerInterceptor是MyBatis-Plus的分页插件，它可以帮助你轻松地实现分页查询功能
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

}
