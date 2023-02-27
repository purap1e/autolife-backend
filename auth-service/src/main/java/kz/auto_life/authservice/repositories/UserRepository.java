package kz.auto_life.authservice.repositories;

import kz.auto_life.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUin(String uin);
}
