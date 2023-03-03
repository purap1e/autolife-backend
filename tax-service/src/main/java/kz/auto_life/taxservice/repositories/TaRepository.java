package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.childs.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaRepository extends JpaRepository<Tax, Long> {
}
