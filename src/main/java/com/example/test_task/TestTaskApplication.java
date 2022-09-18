package com.example.test_task;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(type = SecuritySchemeType.HTTP)
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}
