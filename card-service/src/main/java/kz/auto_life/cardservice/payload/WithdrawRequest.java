package kz.auto_life.cardservice.payload;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class WithdrawRequest {
    private List<WithdrawAttributes> attributes;
    private UUID cardId;
    private UUID serviceId;
}
