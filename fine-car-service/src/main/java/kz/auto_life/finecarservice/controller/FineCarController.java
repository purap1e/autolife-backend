package kz.auto_life.finecarservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Fine for a car API")
@RestController
@RequestMapping("/api/fine/")
public class FineCarController {

    @Operation(summary = "Показать штрафы на авто",
            description = "отображает штрафы на автомобиль")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping()
    public String getFineForCar() {
        return "Добро пожаловать!!!";
    }
}
