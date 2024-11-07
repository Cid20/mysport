package sds_java.mysport.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer rating;
}
