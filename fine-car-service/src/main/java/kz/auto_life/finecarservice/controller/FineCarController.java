package kz.auto_life.finecarservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.finecarservice.payload.FineResponse;
import kz.auto_life.finecarservice.payload.WithdrawRequest;
import kz.auto_life.finecarservice.services.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Fine for a car API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fines")
public class FineCarController {

    private final FineService fineService;

    @Operation(summary = "Показать штрафы по иин на авто",
            description = "отображает штрафы на автомобиль по иин")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<FineResponse> getFineForCar(@RequestParam String iin,
                                            @RequestParam Boolean paid) {
        return fineService.getAll(iin,paid);
    }

    @Operation(summary = "Оплата штрафов",
            description = "оплата штрафов по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/pay")
    public List<FineResponse> payTaxes(@RequestBody WithdrawRequest request) {
        return fineService.update(request);
    }
}
