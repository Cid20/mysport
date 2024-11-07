package sds_java.mysport.payload.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sds_java.mysport.entity.enums.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResUser {
    private Long id;
    private String userName;
    private String phone;
    @JsonProperty("userRole")
    private UserRole userRole;

}
