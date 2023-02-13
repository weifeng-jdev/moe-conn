package com.amano.moeconn.exception.handler;

import com.amano.moeconn.dto.Result;
import com.amano.moeconn.exception.AccessLimitRejectException;
import com.amano.moeconn.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerImpl {
    @ExceptionHandler(BizException.class)
    public Result<Object> handlerBizException(BizException e) {
        return Result.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(AccessLimitRejectException.class)
    public Result<Object> handlerAccessLimitRejectException(AccessLimitRejectException e) {
        return Result.error(e.getErrorCode(), e.getMessage());
    }
}
