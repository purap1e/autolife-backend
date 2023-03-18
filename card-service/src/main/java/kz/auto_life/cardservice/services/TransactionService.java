package kz.auto_life.cardservice.services;

import kz.auto_life.cardservice.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    List<Transaction> getAll();
}
