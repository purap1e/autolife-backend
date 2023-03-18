package kz.auto_life.finecarservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WithdrawAttributes {
    private UUID id;
    private int amount;
    private String grnz;
}
