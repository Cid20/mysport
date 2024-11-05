package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phoneNumber);
    boolean existsByPhone(String phoneNumber);
}
