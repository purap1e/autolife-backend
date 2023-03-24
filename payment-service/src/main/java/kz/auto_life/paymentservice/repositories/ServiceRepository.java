package kz.auto_life.paymentservice.repositories;

import kz.auto_life.paymentservice.models.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Accommodation, UUID> {
    Accommodation findById(String uuid);
}
