package kz.auto_life.shopservice.mappers;

import kz.auto_life.shopservice.models.Item;
import kz.auto_life.shopservice.payload.ItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Service
public class ItemMapper implements Function<Item, ItemDTO> {
    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getAmount(),
                item.getTitle(),
                item.getPrice(),
                item.getImages()
                        .stream()
                        .map(image -> ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/shop/images/" + image.getId())
                        .toList()
        );
    }
}
