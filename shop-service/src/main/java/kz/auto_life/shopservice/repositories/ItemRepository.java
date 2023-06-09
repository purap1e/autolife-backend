package kz.auto_life.shopservice.repositories;

import kz.auto_life.shopservice.models.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);
}
