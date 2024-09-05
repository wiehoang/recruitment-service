package vn.unigap.api.dto.out;

import lombok.Builder;
import lombok.Data;
import vn.unigap.api.entity.JobField;


/**
 * The `JobFieldDtoOut` class represents the Data Transfer Object (DTO)
 * for responding a JobField object.
 */
@Builder
@Data
public class JobFieldDtoOut {

  private Long id;
  private String name;

  /** Converts a `JobField` entity to a `JobFieldDtoOut` DTO. */
  public static JobFieldDtoOut from(JobField jobField) {
    return JobFieldDtoOut.builder()
            .id(jobField.getId())
            .name(jobField.getName())
            .build();
  }

}
