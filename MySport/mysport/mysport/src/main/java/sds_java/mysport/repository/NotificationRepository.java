package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Integer countAllByUserIdAndReadFalse(Long userId);

    List<Notification> findAllByUserId(Long userId);
}
