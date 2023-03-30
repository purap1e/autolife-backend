package kz.auto_life.shopservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.shopservice.models.Item;
import kz.auto_life.shopservice.payload.ItemDTO;
import kz.auto_life.shopservice.payload.ItemRequest;
import kz.auto_life.shopservice.payload.ItemResponse;
import kz.auto_life.shopservice.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Item API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/items/")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "смоздание товара с картинками(доступ только для админов)",
            description = "создание товара с картинками для магазина авто")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping("/create")
    public ResponseEntity<ItemResponse> create(@RequestParam int amount,
                                               @RequestParam String title,
                                               @RequestParam BigDecimal price,
                                               @RequestParam List<MultipartFile> images) {
        return ResponseEntity.status(201).body(itemService.save(amount, title, price, images));
    }

    @Operation(summary = "просмотр 1 товара по id",
            description = "просмотр 1 товара по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ItemDTO create(@RequestBody ItemRequest request) {
        return itemService.get(request);
    }
}
