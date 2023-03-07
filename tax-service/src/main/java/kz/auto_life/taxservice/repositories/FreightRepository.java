package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.childs.Freight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {
}
