package kz.auto_life.stoservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.stoservice.payload.StoDTO;
import kz.auto_life.stoservice.services.StoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "Sto API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sto")
public class StoController {

    private final StoService stoService;

    @Operation(summary = "создание сто (доступ только админам)",
            description = "создание сто")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping("/create")
    public ResponseEntity<UUID> create(@RequestParam String title,
                                       @RequestParam String phone,
                                       @RequestParam String description,
                                       @RequestParam String location,
                                       @RequestParam List<MultipartFile> images) {
        return ResponseEntity.status(201).body(stoService.save(title, phone, description, location, images));
    }

    @Operation(summary = "просмотр 1 СТО по id",
            description = "просмотр 1 СТО по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public StoDTO get(@PathVariable UUID id) {
        return stoService.get(id);
    }

    @Operation(summary = "просмотр всех СТО",
            description = "просмотр всех СТО")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<StoDTO> getAll() {
        return stoService.getAll();
    }
}
