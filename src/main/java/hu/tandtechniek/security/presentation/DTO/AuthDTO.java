package hu.tandtechniek.security.presentation.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthDTO {
    @NotBlank
    public String username;

    @Size(min = 8)
    public String password;

}
