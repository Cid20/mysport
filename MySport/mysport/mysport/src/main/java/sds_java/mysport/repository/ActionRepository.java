package sds_java.mysport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sds_java.mysport.entity.Actions;
import sds_java.mysport.entity.enums.Events;

import java.util.List;

public interface ActionRepository extends JpaRepository<Actions,Long> {
    List<Actions> findAllByEvent(Events events);
}
