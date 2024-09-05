package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * The `SeekerDtoIn` class represents the Data Transfer Object (DTO)
 * for creating and editing a seeker.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerDtoIn {

  @NotNull
  private String name;

  @NotNull
  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  private String address;

  @NotNull
  private Integer province;

}
