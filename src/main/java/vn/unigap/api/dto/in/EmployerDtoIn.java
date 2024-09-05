package vn.unigap.api.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `EmployerDtoIn` class represents the Data Transfer Object (DTO)
 * for creating or editing an employer.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoIn {

  @NotNull
  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
  @Size(max = 255)
  private String email;

  @NotNull
  @Size(max = 255)
  private String name;

  @NotNull
  private int provinceId;

  private String description;

}
