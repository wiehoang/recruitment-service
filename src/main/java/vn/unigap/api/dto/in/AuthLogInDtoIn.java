package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `AuthLogInDtoIn` class represents the Data Transfer Object (DTO)
 * for the login form used in the authentication process.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLogInDtoIn {

  @NotNull(message = "Username cannot be blank")
  private String username;

  @NotNull(message = "Password cannot be blank")
  private String password;

}
