package com.amano.moeconn.exception.handler;

import com.amano.moeconn.dto.Result;
import com.amano.moeconn.exception.AccessLimitRejectException;
import com.amano.moeconn.exception.BizException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerImpl {
    @ExceptionHandler(BizException.class)
    public Result<?> exceptionHandler(BizException e) {
        return Result.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(AccessLimitRejectException.class)
    public Result<?> exceptionHandler(AccessLimitRejectException e) {
        return Result.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> exceptionHandler(MethodArgumentNotValidException e) {
        return Result.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(MessagingException.class)
    public Result<?> exceptionHandler(MessagingException e) {
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "邮件发送失败，请稍后重试！");

    }
}
