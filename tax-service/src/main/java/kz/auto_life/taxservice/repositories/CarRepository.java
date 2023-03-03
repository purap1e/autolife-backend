package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.childs.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByGrnz(String grnz);
}
