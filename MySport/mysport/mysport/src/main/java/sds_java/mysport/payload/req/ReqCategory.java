package sds_java.mysport.payload.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReqCategory {
    private String title;
    private String description;
    private Double price;
}
