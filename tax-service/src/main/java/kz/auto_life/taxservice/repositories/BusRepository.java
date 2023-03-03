package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByGrnz(String grnz);
}
