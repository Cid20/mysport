package sds_java.mysport.payload.req;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReqUser {

    private String userName;

    private String phone;

    private String password;
}
