package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.filters.CustomAuthorizationFilter;
import kz.auto_life.cardservice.modules.Transaction;
import kz.auto_life.cardservice.repositories.TransactionRepository;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        log.info("Saving new transaction to the database");
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        log.info("Fetching all transactions from the database with id '{}'", CustomAuthorizationFilter.userId);
        return transactionRepository.findAllByUserId(Long.parseLong(CustomAuthorizationFilter.userId));
    }
}
