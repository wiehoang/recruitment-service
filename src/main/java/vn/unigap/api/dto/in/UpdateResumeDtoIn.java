package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


/**
 * The `UpdateResumeDtoIn` class represents the Data Transfer Object (DTO)
 * for updating a resume by a seeker.
 */
@Builder
@Data
public class UpdateResumeDtoIn {

  @NotNull
  private String careerObj;

  @NotNull
  private String title;

  @NotNull
  private Integer salary;

  @NotNull
  private String fieldIds;

  @NotNull
  private String provinceIds;

}
