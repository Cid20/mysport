package sds_java.mysport.payload.res;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResUser {

    private Long id;
    private String username;
    private String phone;
    private String userRole;
}
