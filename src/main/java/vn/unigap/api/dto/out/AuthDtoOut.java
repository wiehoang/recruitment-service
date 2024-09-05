package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `AuthDtoOut` class represents the Data Transfer Object (DTO)
 * for responding to authenticate process.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDtoOut {
  private String token;
}
