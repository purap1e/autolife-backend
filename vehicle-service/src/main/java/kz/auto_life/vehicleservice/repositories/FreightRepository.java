package kz.auto_life.vehicleservice.repositories;

import kz.auto_life.vehicleservice.models.childs.Freight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {
    Freight findByGrnz(String grnz);
}
