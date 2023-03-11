package kz.auto_life.cardservice.payload;

import lombok.Getter;

@Getter
public class CardRequest {
    private String fullName;
    private String cardNumber;
    private int cardMonth;
    private int cardYear;
    private String cvv;
}
