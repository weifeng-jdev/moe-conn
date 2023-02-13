package com.amano.moeconn.security.handler;

import com.amano.moeconn.constant.CommonConstant;
import com.amano.moeconn.dto.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(CommonConstant.APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
        Result<Object> result = Result.error(HttpStatus.FORBIDDEN.value(), "权限不足！");
        String rs = objectMapper.writer().writeValueAsString(result);
        writer.write(rs);
        writer.flush();
    }
}
