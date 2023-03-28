package kz.auto_life.cardservice.services;


import kz.auto_life.cardservice.modules.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    List<Transaction> getAll();
}
