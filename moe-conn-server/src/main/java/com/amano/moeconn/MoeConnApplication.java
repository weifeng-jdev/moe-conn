package com.amano.moeconn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan("com.amano.moeconn.dao")
public class MoeConnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoeConnApplication.class, args);
    }

}
