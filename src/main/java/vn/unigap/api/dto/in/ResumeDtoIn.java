package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


/**
 * The `ResumeDtoIn` class represents the Data Transfer Object (DTO)
 * for creating a resume by a seeker.
 */
@Builder
@Data
public class ResumeDtoIn {

  @NotNull
  private Long seekerId;

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
