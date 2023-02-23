package kz.auto_life.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Operation(summary = "Авторизация",
            description = "Апи для авторизации и получение токена")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/login")
    public String test() {
        return "Добро пожаловать!!!";
    }
}
