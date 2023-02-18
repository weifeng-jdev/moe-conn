package com.amano.moeconn.interceptor.handler;

import com.amano.moeconn.exception.AccessLimitRejectException;
import com.amano.moeconn.interceptor.AccessLimiting;
import com.amano.moeconn.util.IPUtil;
import com.amano.moeconn.util.RedisUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.amano.moeconn.constant.CacheConstant.ACCESS_LIMITING_KEY;

public class AccessLimitingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取请求方法
        HandlerMethod method = (HandlerMethod) handler;
        if (checkAccessLimit(request, method)) {
            // 超限制
            throw new AccessLimitRejectException();
        }
        return true;
    }

    public boolean checkAccessLimit(HttpServletRequest request, HandlerMethod method) {
        AccessLimiting methodAnnotation = method.getMethodAnnotation(AccessLimiting.class);
        String name = method.getMethod().getName();
        if (Objects.isNull(methodAnnotation)) {
            return false;
        }
        // 通过redis key自增计算流量是否超限
        String clintIp = IPUtil.getIpAddress(request);
        Long limit = methodAnnotation.limitOnUnitTime();
        TimeUnit timeUnit = methodAnnotation.timeUnit();
        Long incrExpire = RedisUtil.incrExpire(String.format(ACCESS_LIMITING_KEY, name, clintIp), limit, timeUnit);
        return incrExpire > limit;
    }
}
