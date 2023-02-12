package com.amano.moeconn.controller;

import com.amano.moeconn.dto.Result;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.interceptor.AccessLimiting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    @AccessLimiting(limitOnUnitTime = 1L, timeUnit = TimeUnit.MINUTES)
    public Result<String> hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication.getPrincipal() instanceof UserDetailsDTO) {
            username = ((UserDetailsDTO) authentication.getPrincipal()).getUsername();
        } else {
            username = (String) authentication.getPrincipal();
        }
        return Result.OK("success", username);
    }

    @GetMapping("/auth/hello")
    public Result<String> authHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication.getPrincipal() instanceof UserDetailsDTO) {
            username = ((UserDetailsDTO) authentication.getPrincipal()).getUsername();
        } else {
            username = (String) authentication.getPrincipal();
        }
        return Result.OK("success", username);
    }

    @GetMapping("/user/hello")
    public Result<String> userHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication.getPrincipal() instanceof UserDetailsDTO) {
            username = ((UserDetailsDTO) authentication.getPrincipal()).getUsername();
        } else {
            username = (String) authentication.getPrincipal();
        }
        return Result.OK("success", username);
    }
}
