package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSignUpDtoIn {

    @NotNull(message = "Username cannot be blank")
    private String username;

    @NotNull(message = "Password cannot be blank")
    private String password;

    @NotNull
    private String role;
}
