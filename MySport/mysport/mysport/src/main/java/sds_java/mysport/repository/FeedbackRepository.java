package sds_java.mysport.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    Page<Feedback> findAllByCategoryId(Long categoryId, Pageable pageable);
}
