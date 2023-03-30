package kz.auto_life.shopservice.services.impls;

import kz.auto_life.shopservice.mappers.ItemMapper;
import kz.auto_life.shopservice.models.Image;
import kz.auto_life.shopservice.models.Item;
import kz.auto_life.shopservice.payload.ItemDTO;
import kz.auto_life.shopservice.payload.ItemResponse;
import kz.auto_life.shopservice.payload.PurchaseAttributes;
import kz.auto_life.shopservice.repositories.ItemRepository;
import kz.auto_life.shopservice.services.ItemService;
import kz.auto_life.shopservice.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponse save(int amount, String title, BigDecimal price, List<MultipartFile> images) {
        log.info("Saving item with images");
        Item item = new Item();
        item.setAmount(amount);
        item.setTitle(title);
        item.setPrice(price);
        item.setImages(getList(images));
        itemRepository.saveAndFlush(item);
        return new ItemResponse(item.getId());
    }

    @Override
    @Transactional
    public ItemDTO get(UUID uuid) {
        log.info("fetching item with id {}", uuid);
        return itemRepository.findById(uuid).map(itemMapper).orElseThrow(() -> new RuntimeException("item not found"));
    }

    @Override
    @Transactional
    public List<ItemDTO> getAll(BigDecimal min, BigDecimal max, int page, int size) {
        log.info("Fetching all items");
        return itemRepository.findAllByPriceBetween(min, max, PageRequest.of(page, size))
                .stream()
                .map(itemMapper).toList();
    }

    @Override
    @Transactional
    public ItemDTO update(UUID id, int newAmount, BigDecimal newPrice, String newTitle, List<MultipartFile> newImages) {
        log.info("updating item with id {}", id);
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("item not found"));
        item.setAmount(newAmount);
        item.setPrice(newPrice);
        item.setTitle(newTitle);
        item.setImages(getList(newImages));
        itemRepository.save(item);

        return itemRepository.findById(id).map(itemMapper).orElseThrow(() -> new RuntimeException("item not found"));
    }

    public List<Image> getList(List<MultipartFile> images) {
        List<Image> files = new ArrayList<>();
        images.forEach(file -> {
            try {
                Image image = new Image();
                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());
                image.setData(ImageUtils.compressImage(file.getBytes()));
                files.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return files;
    }

    @Override
    @Transactional
    public List<ItemDTO> purchase(List<PurchaseAttributes> attributes) {
        List<Item> items = new ArrayList<>();
        attributes.forEach(attribute -> {
            Item item = itemRepository.findById(attribute.getId()).orElseThrow(() -> new RuntimeException("item not found"));
            item.setAmount(item.getAmount() - attribute.getCount());
            itemRepository.save(item);

            Item response = new Item();
            response.setId(item.getId());
            response.setTitle(item.getTitle());
            response.setPrice(item.getPrice());
            response.setAmount(attribute.getCount());
            response.setImages(item.getImages());
            items.add(response);
        });
        return items.stream().map(itemMapper).toList();
    }
}
