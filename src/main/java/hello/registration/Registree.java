package hello.registration;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ValidNewUser
public class Registree {
    @NotNull
    @Size(min = 4, max = 64)
    private String username;
//    @ValidPassword
    private String password;
    private String password2;
}
