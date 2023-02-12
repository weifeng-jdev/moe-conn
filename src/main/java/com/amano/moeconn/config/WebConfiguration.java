package com.amano.moeconn.config;

import com.amano.moeconn.interceptor.handler.AccessLimitingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public AccessLimitingInterceptor accessLimitingInterceptor() {
        return new AccessLimitingInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitingInterceptor());
    }
}
