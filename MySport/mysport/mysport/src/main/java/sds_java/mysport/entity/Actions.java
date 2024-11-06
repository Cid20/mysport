package sds_java.mysport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import sds_java.mysport.entity.enums.Events;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private File file;
    @Enumerated(EnumType.STRING)
    private Events event;
    @CreationTimestamp
    private LocalDateTime time;
}
