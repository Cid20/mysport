package sds_java.mysport.payload.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResFeedback {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String text;
    private int rate;
    private LocalDate date;
}
