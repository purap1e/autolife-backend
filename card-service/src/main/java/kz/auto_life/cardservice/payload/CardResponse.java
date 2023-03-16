package kz.auto_life.cardservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CardResponse {
    private UUID cardId;
    private String name;
    private String lastNumbersOfCard;
    private BigDecimal amount;
    private int month;
    private int year;
}
