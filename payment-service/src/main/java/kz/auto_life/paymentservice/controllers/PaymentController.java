package kz.auto_life.paymentservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.paymentservice.payload.WithdrawRequest;
import kz.auto_life.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Payment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Оплата налогов и штрафов",
            description = "оплата налогов и штрафов через карточку пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public List<?> pay(@RequestBody WithdrawRequest request) {
        return paymentService.pay(request);
    }
}
