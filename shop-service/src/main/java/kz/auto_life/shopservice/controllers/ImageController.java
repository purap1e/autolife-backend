package kz.auto_life.shopservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.shopservice.models.Image;
import kz.auto_life.shopservice.services.ImageService;
import kz.auto_life.shopservice.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Image API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/images")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "выдает картинку по id",
            description = "выдает картинку по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public ResponseEntity<?> download(@PathVariable UUID id) {
        Image image = imageService.get(id);
        byte[] imageData = ImageUtils.decompressImage(image.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }
}
