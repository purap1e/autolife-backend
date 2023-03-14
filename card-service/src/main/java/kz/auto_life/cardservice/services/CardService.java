package kz.auto_life.cardservice.services;

import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawRequest;

import java.util.List;

public interface CardService {

    CardResponse saveUserToCard();
    String withdraw(WithdrawRequest request);
    List<CardResponse> getAll();


}
