package kz.auto_life.cardservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.cardservice.models.Transaction;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Transaction API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Вывод всех транзаций",
            description = "список всех транзакий")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @Operation(summary = "Вывод всех транзаций с какого то периода",
            description = "список всех транзакий с какого то периода")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/date")
    public List<Transaction> getAllByFilteringDate(@RequestParam int days) {
        return transactionService.getAllByDate(days);
    }
}
