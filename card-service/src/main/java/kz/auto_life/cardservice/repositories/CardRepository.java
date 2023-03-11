package kz.auto_life.cardservice.repositories;

import kz.auto_life.cardservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserId(Long id);
}
