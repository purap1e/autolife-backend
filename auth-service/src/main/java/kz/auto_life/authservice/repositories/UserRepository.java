package kz.auto_life.authservice.repositories;

import kz.auto_life.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);
    User findByUin(String uin);
}
