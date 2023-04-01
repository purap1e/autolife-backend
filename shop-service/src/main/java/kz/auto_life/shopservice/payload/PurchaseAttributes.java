package kz.auto_life.shopservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PurchaseAttributes {
    private UUID id;
    private int count;
}
