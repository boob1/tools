package org.hongda.tranceId.config;

import org.hongda.tranceId.constant.Constants;
import org.hongda.tranceId.util.TraceIdUtil;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyIntercepter
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:27
 **/
@Configuration
public class MyIntercepter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tid = request.getHeader(Constants.TRANCE_ID);
        if (StringUtils.isEmpty(tid)) {
            tid = TraceIdUtil.getTraceId();
        }
        MDC.put(Constants.TRANCE_ID, tid);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(Constants.TRANCE_ID);
    }
}
