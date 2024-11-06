package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.VideoViews;

public interface VdViewsRepository extends JpaRepository<VideoViews,Long> {
    Integer countByFileId(Long fileId);
}
