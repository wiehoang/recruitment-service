package vn.unigap.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The `EmployerDtoOut` class represents the Data Transfer Object (DTO)
 * for responding an employer object.
 */
@Builder
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployerDtoOut {

  private Long id;
  private String email;
  private String name;
  private Integer provinceId;
  private String provinceName;
  private String description;

}
