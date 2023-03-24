package kz.auto_life.authservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Смена пароля",
            description = "Ендпоинт для смены пароля")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/update")
    public String registerNewUser(@RequestParam String phone,
                                  @RequestParam String password) {
        return userService.updatePassword(phone, password);
    }
}
