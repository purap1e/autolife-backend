package kz.auto_life.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping
    public String getTest() {
        return "Добро пожоловать в authservice!!33333333";
    }

    @GetMapping("/uuid")
    public UUID generateUUID() {
        return UUID.randomUUID();
    }
}
