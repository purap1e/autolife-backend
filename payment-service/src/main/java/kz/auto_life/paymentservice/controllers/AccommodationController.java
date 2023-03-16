package kz.auto_life.paymentservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.paymentservice.models.Accommodation;
import kz.auto_life.paymentservice.payload.AccommodationRequest;
import kz.auto_life.paymentservice.payload.AccommodationResponse;
import kz.auto_life.paymentservice.services.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Accommodation API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Operation(summary = "Создание услуг",
            description = "создание услуг может сделать только админ")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public Accommodation save(@RequestBody AccommodationRequest request) {
        return accommodationService.save(request);
    }

    @Operation(summary = "Вывод всех услуг",
            description = "список всех услуг")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<AccommodationResponse> getAll() {
        return accommodationService.getAll();
    }
}
