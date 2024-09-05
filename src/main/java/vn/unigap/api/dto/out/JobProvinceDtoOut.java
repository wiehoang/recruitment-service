package vn.unigap.api.dto.out;

import lombok.Builder;
import lombok.Data;
import vn.unigap.api.entity.JobProvince;


/**
 * The `JobProvinceDtoOut` class represents the Data Transfer Object (DTO)
 * for responding a JobProvince object.
 */
@Builder
@Data
public class JobProvinceDtoOut {

  private Integer id;
  private String name;

  /** Converts a `JobProvince` entity to a `JobProvinceDtoOut` DTO. */
  public static JobProvinceDtoOut from(JobProvince jobProvince) {
    return JobProvinceDtoOut.builder()
            .id(jobProvince.getId())
            .name(jobProvince.getName())
            .build();
  }

}
