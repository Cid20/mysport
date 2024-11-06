package sds_java.mysport.payload.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ResAction {
    private Long id;
    private String username;
    private Long fileId;
    private LocalDateTime time;
}
