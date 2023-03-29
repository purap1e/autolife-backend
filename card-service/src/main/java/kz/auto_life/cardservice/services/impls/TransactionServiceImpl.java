package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.filters.CustomAuthorizationFilter;
import kz.auto_life.cardservice.models.Transaction;
import kz.auto_life.cardservice.repositories.TransactionRepository;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

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
        return transactionRepository.findAllByUserId(UUID.fromString(CustomAuthorizationFilter.userId));
    }

    @Override
    public List<Transaction> getAllByDate(int days) {
        log.info("Fetching all transaction by filtering date");
        LocalDateTime time = LocalDateTime.now().minus(days, ChronoUnit.DAYS);
        return transactionRepository.findAllByCreatedAtGreaterThan(time);
    }
}
