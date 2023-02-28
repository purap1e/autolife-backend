package kz.auto_life.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.authservice.payload.UserRegisterRequest;
import kz.auto_life.authservice.payload.UserRegisterResponse;
import kz.auto_life.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Регистрация",
            description = "Ендпоинт для регистрации")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping("/registration")
    public ResponseEntity<UserRegisterResponse> registerNewUser(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.status(201).body(UserRegisterRequest.from(userService.register(request)));
    }
}
