package kz.auto_life.paymentservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WithdrawAttributes {
    private UUID id;
    private int amount;
    private String description;
    private int count;
}
