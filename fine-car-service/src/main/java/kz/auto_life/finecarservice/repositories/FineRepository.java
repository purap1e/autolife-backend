package kz.auto_life.finecarservice.repositories;

import kz.auto_life.finecarservice.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findAllByUserIinAndPaid(String userIin, Boolean paid);
}
