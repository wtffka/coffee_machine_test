package com.example.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Operation(summary = "Show welcome page")
    @ApiResponse(responseCode = "200", description = "Welcome page shown")
    @GetMapping
    public String welcomePage() {
        return "Тестовое задание AISA IT-Service. С краткой инструкцией по работе приложения можно ознакомиться, " +
                "прочитав README на страничке репозитория.";
    }
}
