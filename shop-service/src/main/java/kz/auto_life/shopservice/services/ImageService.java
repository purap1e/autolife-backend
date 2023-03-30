package kz.auto_life.shopservice.services;

import kz.auto_life.shopservice.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {
    Image get(UUID id);
}
