package sds_java.mysport.payload.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReqUser {
    @NotBlank
    private String username;
    @NotBlank
    private String phone;

    private String password;
}
