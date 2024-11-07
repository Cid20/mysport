package sds_java.mysport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean read;

    @ManyToOne
    private File file;

    @ManyToOne
    private User user;

    private boolean contact;

    @ManyToOne
    private User registrant;
}
