package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.childs.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
