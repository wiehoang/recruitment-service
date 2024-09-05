package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `JobsDtoIn` class represents the Data Transfer Object (DTO)
 * for creating a job by an employer.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class JobsDtoIn {

  @NotNull
  private String title;

  @NotNull
  private Long employerId;

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
