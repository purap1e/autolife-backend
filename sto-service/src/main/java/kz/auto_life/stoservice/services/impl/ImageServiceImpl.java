package kz.auto_life.stoservice.services.impl;

import kz.auto_life.stoservice.exceptions.InvalidCredentialsException;
import kz.auto_life.stoservice.models.Image;
import kz.auto_life.stoservice.payload.ResponseMessage;
import kz.auto_life.stoservice.repositories.ImageRepository;
import kz.auto_life.stoservice.services.ImageService;
import kz.auto_life.stoservice.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<?> get(UUID id) {
        log.info("fetching image by id");
        Image image = imageRepository.findById(id).orElseThrow(() -> new InvalidCredentialsException(new ResponseMessage("Image not found")));
        byte[] imageData = ImageUtils.decompressImage(image.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }
}
