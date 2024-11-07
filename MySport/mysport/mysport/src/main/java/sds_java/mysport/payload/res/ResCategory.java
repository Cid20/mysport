package sds_java.mysport.payload.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResCategory {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer rating;
}
