package kz.auto_life.stoservice.repositories;

import kz.auto_life.stoservice.models.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoRepository extends JpaRepository<Sto, UUID> {
}
