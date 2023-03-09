package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByGrnz(String grnz);
}
