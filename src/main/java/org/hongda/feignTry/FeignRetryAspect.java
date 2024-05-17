package org.hongda.feignTry;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FeignRetryAspect
 * @Description 拿到注解上的值进行业务处理
 * @Author liuyibo
 * @Date 2024/5/17 11:39
 **/

@Aspect
@Component
@Slf4j
public class FeignRetryAspect {

    @Around("@annotation(FeignRetry)")
    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable{
        Method method =getCurrentMethod(joinPoint);
        FeignRetry feignRetry = method.getAnnotation(FeignRetry.class);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(prepareBackOffPolicy(feignRetry));
        retryTemplate.setRetryPolicy(prepareSimpleRetryPolicy(feignRetry));

        // 重试
        return retryTemplate.execute(arg0 -> {
            int retryCount = arg0.getRetryCount();
            log.info("Sending request method: {}, max attempt: {}, delay: {}, retryCount: {}",
                    method.getName(),
                    feignRetry.maxAttempt(),
                    feignRetry.backoff().delay(),
                    retryCount
            );
            return joinPoint.proceed(joinPoint.getArgs());
        });
    }

    private BackOffPolicy prepareBackOffPolicy(FeignRetry feignRetry) {
        if (feignRetry.backoff().multiplier() != 0) {
            ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
            backOffPolicy.setInitialInterval(feignRetry.backoff().delay());
            backOffPolicy.setMaxInterval(feignRetry.backoff().maxDelay());
            backOffPolicy.setMultiplier(feignRetry.backoff().multiplier());
            return backOffPolicy;
        } else {
            FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
            fixedBackOffPolicy.setBackOffPeriod(feignRetry.backoff().delay());
            return fixedBackOffPolicy;
        }
    }


    private SimpleRetryPolicy prepareSimpleRetryPolicy(FeignRetry feignRetry) {
        Map<Class<? extends Throwable>, Boolean> policyMap = new HashMap<>();
        policyMap.put(RetryableException.class, true);  // Connection refused or time out
        policyMap.put(ClientException.class, true);     // Load balance does not available (cause of RunTimeException)
        if (feignRetry.include().length != 0) {
            for (Class<? extends Throwable> t : feignRetry.include()) {
                policyMap.put(t, true);
            }
        }
        return new SimpleRetryPolicy(feignRetry.maxAttempt(), policyMap, true);
    }

    private Method getCurrentMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

}
