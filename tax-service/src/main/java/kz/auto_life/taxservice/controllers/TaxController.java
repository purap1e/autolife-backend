package kz.auto_life.taxservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.taxservice.exceptions.UnauthorizedException;
import kz.auto_life.taxservice.payload.TaxRequest;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<TaxResponse>> getAllTaxes(@RequestParam String iin,
                                                         @RequestParam int status) {
        List<TaxResponse> taxes = taxService.getAllByIinAndStatus(iin, status)
                .stream()
                .map(x -> new TaxResponse(
                        x.getId(),
                        x.getUserIin(),
                        x.getGrnz(),
                        x.getType(),
                        x.getAmount(),
                        x.getStatus()))
                .toList();
        return ResponseEntity.ok().body(taxes);
    }

    @Operation(summary = "Оплата налогов",
            description = "оплата налогов по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/pay")
    public ResponseEntity<List<TaxResponse>> payTaxes(@RequestBody TaxRequest taxRequest) {
        List<TaxResponse> taxes = taxService.payTaxes(taxRequest.getIds())
                .stream()
                .map(x -> new TaxResponse(
                        x.getId(),
                        x.getUserIin(),
                        x.getGrnz(),
                        x.getType(),
                        x.getAmount(),
                        x.getStatus()))
                .toList();
        return ResponseEntity.ok().body(taxes);
    }

    @Operation(summary = "Счет на налог (имеет доступ только админ)",
            description = "процесс счета налогов")
    @PostMapping("/private/process")
    public void process(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.equals("Basic YWRtaW46cGFzc3dvcmQ=")) {
            throw new UnauthorizedException("Invalid credentials");
        }
        taxService.process();
    }
}
