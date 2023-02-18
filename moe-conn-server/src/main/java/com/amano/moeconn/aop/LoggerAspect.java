package com.amano.moeconn.aop;

import cn.hutool.json.JSONUtil;
import com.amano.moeconn.annotation.Log;
import com.amano.moeconn.domain.SysLogDO;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.service.SysLogService;
import com.amano.moeconn.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.amano.moeconn.constant.CommonConstant.ANONYMOUS_ACCESS;


/**
 * aop日志切面
 */
@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Resource
    private SysLogService sysLogService;

    @Pointcut(value = "@annotation(log)", argNames = "log")
    public void pointcut(Log log) {
    }

    /**
     * 环绕日志切面逻辑
     */
    @Around(value = "pointcut(log)", argNames = "joinPoint,log")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        // 初始化syslog注解相关的内容
        SysLogDO sysLogDO = this.initLogDo(log);
        // 处理请求相关的内容
        this.processRequest(sysLogDO);
        try {
            // 执行切面方法
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            sysLogDO.setOperResult(JSONUtil.toJsonStr(result));
            sysLogDO.setOperTimeCast(System.currentTimeMillis() - startTime);
            return result;
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            sysLogService.save(sysLogDO);
        }
    }

    private SysLogDO initLogDo(Log log) {
        SysLogDO sysLogDO = new SysLogDO();
        // 操作模块和操作类型
        sysLogDO.setOperModule(log.module());
        sysLogDO.setOperType(log.type());
        sysLogDO.setOperDesc(log.value());
        // 操作人信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            // 匿名访问接口
            sysLogDO.setOperUserId(ANONYMOUS_ACCESS);
        } else {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) principal;
            sysLogDO.setOperUserId(userDetailsDTO.getId());
        }
        return sysLogDO;
    }

    private void processRequest(SysLogDO sysLogDO) {
        HttpServletRequest request
                = (HttpServletRequest) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        sysLogDO.setOperUri(Optional.ofNullable(request).orElseGet(null).getRequestURI());
        sysLogDO.setRequestIp(IPUtil.getIpAddress(request));
        // 请求的参数
        String params = converMap(request.getParameterMap());
        sysLogDO.setOperParams(params);
    }

    public String converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return JSONUtil.toJsonStr(rtnMap);
    }
}
