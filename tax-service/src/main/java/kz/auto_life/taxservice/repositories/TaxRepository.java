package kz.auto_life.taxservice.repositories;

import kz.auto_life.taxservice.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaxRepository extends JpaRepository<Tax, UUID> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM taxes INNER JOIN vehicles ON taxes.vehicle_id = vehicles.id AND vehicles.user_iin=:iin AND taxes.paid=:paid")
    List<Tax> findALlByIInAndPaid(String iin, Boolean paid);
}
