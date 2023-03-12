package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findAllByUserIinAndPaid(String iin, Boolean paid);
}
