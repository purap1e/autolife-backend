package kz.auto_life.shopservice.services;

import kz.auto_life.shopservice.payload.ItemDTO;
import kz.auto_life.shopservice.payload.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ItemService {
    ItemResponse save(int amount, String title, BigDecimal price, List<MultipartFile> images);
    ItemDTO get(UUID uuid);
    List<ItemDTO> getAll(BigDecimal min, BigDecimal max, int page, int size);

    ItemDTO update(UUID id, int newAmount, BigDecimal newPrice, String newTitle, List<MultipartFile> newImages);
}
