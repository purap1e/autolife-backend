package kz.auto_life.vehicleservice.repositories;

import kz.auto_life.vehicleservice.models.childs.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByGrnz(String grnz);
}
