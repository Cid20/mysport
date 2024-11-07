package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.Category;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    boolean existsByTitle(String title);
}
