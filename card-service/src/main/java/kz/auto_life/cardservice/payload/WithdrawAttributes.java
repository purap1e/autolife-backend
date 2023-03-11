package kz.auto_life.cardservice.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawAttributes {
    private Long id;
    private int amount;
}
