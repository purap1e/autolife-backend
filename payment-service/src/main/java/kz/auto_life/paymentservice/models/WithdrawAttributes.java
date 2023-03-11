package kz.auto_life.paymentservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawAttributes {
    private Long id;
    private int amount;
    private String grnz;
}
