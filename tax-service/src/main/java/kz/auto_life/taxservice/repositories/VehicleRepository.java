package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
