package kz.auto_life.vehicleservice.repositories;

import kz.auto_life.vehicleservice.models.childs.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByGrnz(String grnz);
}
