package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findAllByUserIinAndPaid(String iin, Boolean paid);
}
