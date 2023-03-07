package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.childs.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
