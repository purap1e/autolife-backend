package kz.auto_life.cardservice.repositories;

import kz.auto_life.cardservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUserId(UUID uuid);
    List<Transaction> findAllByCreatedAtGreaterThan(LocalDateTime l);
}
