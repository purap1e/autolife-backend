package kz.auto_life.finecarservice.repositories;

import kz.auto_life.finecarservice.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FineRepository extends JpaRepository<Fine, UUID> {
    List<Fine> findAllByUserIinAndPaid(String userIin, Boolean paid);
}
