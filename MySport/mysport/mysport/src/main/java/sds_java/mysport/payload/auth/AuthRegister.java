package sds_java.mysport.payload.auth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import sds_java.mysport.entity.enums.UserRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegister {

    @Size(min = 3, max = 30)
    @NotBlank
    private String username;

    @Pattern(regexp = "^998([0-9][012345789]|[0-9][125679]|7[01234569])[0-9]{7}$",
            message = "Invalid Uzbekistan phone number")
    private String phone;
    @Size(min = 5, max = 12)
    private String password;
    private UserRole userRole;
}