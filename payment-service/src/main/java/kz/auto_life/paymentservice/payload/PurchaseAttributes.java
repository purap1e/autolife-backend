package kz.auto_life.paymentservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseAttributes {
    private UUID id;
    private int count;
}
