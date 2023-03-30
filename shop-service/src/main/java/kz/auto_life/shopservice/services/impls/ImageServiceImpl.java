package kz.auto_life.shopservice.services.impls;

import kz.auto_life.shopservice.models.Image;
import kz.auto_life.shopservice.repositories.ImageRepository;
import kz.auto_life.shopservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image get(UUID id) {
        log.info("fetching image by id");
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("image not found"));
    }
}
