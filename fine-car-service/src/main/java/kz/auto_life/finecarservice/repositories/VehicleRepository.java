package kz.auto_life.finecarservice.repositories;

import kz.auto_life.finecarservice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Vehicle findByGrnz(String grnz);
}
