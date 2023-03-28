package kz.auto_life.finecarservice.repositories;

import kz.auto_life.finecarservice.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FineRepository extends JpaRepository<Fine, UUID> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM fines INNER JOIN vehicles ON fines.vehicle_id = vehicles.id AND vehicles.user_iin=:iin AND fines.paid=:paid")
    List<Fine> findALlByIInAndPaid(String iin, Boolean paid);
}
