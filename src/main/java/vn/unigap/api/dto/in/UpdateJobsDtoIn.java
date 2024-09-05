package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `UpdateJobsDtoIn` class represents the Data Transfer Object (DTO)
 * for updating a job by an employer.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateJobsDtoIn {

  @NotNull
  private String title;

  @NotNull
  private Integer quantity;

  @NotNull
  private String description;

  @NotNull
  private String fieldIds;

  @NotNull
  private String provinceIds;

  @NotNull
  private Integer salary;

  @NotNull
  private Date expiredAt = new Date();

}
