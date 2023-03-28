package kz.auto_life.cardservice.mappers;

import kz.auto_life.cardservice.modules.Card;
import kz.auto_life.cardservice.payload.CardResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CardMapper implements Function<Card, CardResponse> {
    @Override
    public CardResponse apply(Card card) {
        return new CardResponse(
                card.getId(),
                card.getFullName(),
                "************" + card.getCardNumber().substring(15),
                card.getAmount(),
                card.getMonth(),
                card.getYear()
        );
    }
}