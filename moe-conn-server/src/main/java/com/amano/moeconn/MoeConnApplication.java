package com.amano.moeconn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class MoeConnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoeConnApplication.class, args);
    }

}
