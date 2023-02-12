package com.amano.moeconn.security.handler;

import com.amano.moeconn.constant.CommonConstant;
import com.amano.moeconn.dto.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(CommonConstant.APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
        Result<Object> result = Result.OK().error500(exception.getMessage());
        String rs = objectMapper.writer().writeValueAsString(result);
        writer.write(rs);
        writer.flush();
    }
}
