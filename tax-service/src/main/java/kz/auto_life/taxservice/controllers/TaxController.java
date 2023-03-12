package kz.auto_life.taxservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.payload.WithdrawRequest;
import kz.auto_life.taxservice.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tax API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/taxes")
public class TaxController {

    private final TaxService taxService;

    @Operation(summary = "Выдает все налоги",
            description = "Выдает все налоги на автомобили по иин и статусу (1 - не оплачен, 2 - оплачен)")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<TaxResponse> getAllTaxes(@RequestParam String iin,
                                                         @RequestParam Boolean paid) {
        return taxService.getAllForUser(iin, paid);
    }

    @Operation(summary = "Оплата налогов",
            description = "оплата налогов по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/pay")
    public List<TaxResponse> payTaxes(@RequestBody WithdrawRequest request) {
        return taxService.payTaxes(request);
    }

    @Operation(summary = "Счет на налог (имеет доступ только админ)",
            description = "процесс счета налогов")
    @PostMapping("/private/process")
    public void process() {
        taxService.process();
    }
}
