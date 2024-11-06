package sds_java.mysport.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phoneNumber);
    boolean existsByPhone(String phoneNumber);
    boolean existsById(Long id);
    Page<User> findAllByUserRole(UserRole role, Pageable pageable);
    @Query("SELECT u FROM User u WHERE " +
            "(:role IS NULL OR u.userRole = :role) AND " +
            "(" +
            "(:field IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :field, '%'))) OR " +
            "(:field IS NULL OR u.phone LIKE CONCAT('%', :field, '%'))" +
            ")")
    List<User> searchUserRole(@Param("field") String field,
                              @Param("role") UserRole role);
}
