package kz.auto_life.cardservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class WithdrawAttributes {
    private UUID id;
    private BigDecimal amount;
    private String grnz;
}
