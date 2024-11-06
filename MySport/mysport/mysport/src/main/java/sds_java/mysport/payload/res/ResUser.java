package sds_java.mysport.payload.res;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ResUser {

    private Long id;
    private String username;
    private String phone;
    private String userRole;
}
