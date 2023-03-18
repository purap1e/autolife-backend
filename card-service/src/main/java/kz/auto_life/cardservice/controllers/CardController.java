package kz.auto_life.cardservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawRequest;
import kz.auto_life.cardservice.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Card API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Создание карточки",
            description = "создание банковской карточки для пользователя")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping("/create")
    public ResponseEntity<CardResponse> createCard() {
        return ResponseEntity.status(201).body(cardService.saveUserToCard());
    }

    @Operation(summary = "Оплата через карту",
            description = "оплата через карту пользователя с определенной суммой")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/withdraw")
    public ResponseEntity<String> getMessage(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok().body(cardService.withdraw(request));
    }

    @Operation(summary = "вывести все карточки",
            description = "вывести все карточки пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<CardResponse> getAll() {
        return cardService.getAll();
    }
}
