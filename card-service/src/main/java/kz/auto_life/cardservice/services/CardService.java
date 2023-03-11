package kz.auto_life.cardservice.services;

import kz.auto_life.cardservice.models.Card;
import kz.auto_life.cardservice.payload.CardRequest;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawRequest;

import java.util.List;

public interface CardService {

    CardResponse saveUserToCard(CardRequest request);
    String withdraw(WithdrawRequest request);
    List<CardResponse> getAll();


}
