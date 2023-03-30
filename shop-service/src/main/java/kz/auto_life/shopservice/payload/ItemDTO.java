package kz.auto_life.shopservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
    private UUID id;
    private int amount;
    private String title;
    private BigDecimal price;
    private List<String> imageUrls;
}
