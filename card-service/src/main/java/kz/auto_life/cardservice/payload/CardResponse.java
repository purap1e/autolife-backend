package kz.auto_life.cardservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardResponse {
    private Long cardId;
    private String name;
    private String lastNumbersOfCard;
    private int amount;
    private int month;
    private int year;
}
