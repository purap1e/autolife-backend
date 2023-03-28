package kz.auto_life.cardservice.repositories;

import kz.auto_life.cardservice.modules.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findAllByUserId(UUID id);
}
