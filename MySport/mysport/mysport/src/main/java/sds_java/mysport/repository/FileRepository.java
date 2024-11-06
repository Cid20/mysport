package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.File;

public interface FileRepository extends JpaRepository<File,Long> {
}
