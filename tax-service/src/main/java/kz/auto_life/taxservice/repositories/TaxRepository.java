package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaxRepository extends JpaRepository<Tax, UUID> {
    List<Tax> findAllByUserIinAndPaid(String iin, Boolean paid);
}
