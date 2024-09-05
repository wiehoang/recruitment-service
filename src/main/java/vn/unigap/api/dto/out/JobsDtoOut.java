package vn.unigap.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Jobs;


/**
 * The `JobsDtoOut` class represents the Data Transfer Object (DTO)
 * for responding a Jobs object.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobsDtoOut {

  private Long id;
  private String title;
  private Long employerId;
  private String employerName;
  private Integer quantity;
  private String description;
  private Set<Long> fieldIds;
  private Set<Long> provinceIds;
  private Set<JobFieldDtoOut> fields;
  private Set<JobProvinceDtoOut> provinces;
  private Integer salary;
  private Date expiredAt = new Date();

  /** Creates a `JobsDtoOut` instance from a `Jobs` entity
   * with field and province IDs that stored in a set.
   */
  public static JobsDtoOut create(Jobs jobs, Set<Long> fieldIds, Set<Long> provinceIds) {
    return JobsDtoOut.builder()
            .title(jobs.getTitle())
            .employerId(jobs.getEmployer().getId())
            .quantity(jobs.getQuantity())
            .description(jobs.getDescription())
            .fieldIds(fieldIds)
            .provinceIds(provinceIds)
            .salary(jobs.getSalary())
            .expiredAt(jobs.getExpiredAt())
            .build();
  }

  /** Updates a `JobsDtoOut` instance from a `Jobs` entity
   * with field and province IDs that stored in a set.
   */
  public static JobsDtoOut update(Jobs jobs, Set<Long> fieldIds, Set<Long> provinceIds) {
    return JobsDtoOut.builder()
            .id(jobs.getId())
            .title(jobs.getTitle())
            .quantity(jobs.getQuantity())
            .description(jobs.getDescription())
            .fieldIds(fieldIds)
            .provinceIds(provinceIds)
            .salary(jobs.getSalary())
            .expiredAt(jobs.getExpiredAt())
            .build();
  }

  /** Retrieves a `JobsDtoOut` instance from a `Jobs` entity
   * with sets of JobFieldDtoOut and JobProvinceDtoOut objects.
   */
  public static JobsDtoOut get(Jobs jobs, Set<JobFieldDtoOut> fields,
                               Set<JobProvinceDtoOut> provinces) {
    Long employerId = null;
    String employerName = null;
    if (jobs.getEmployer() != null) {
      employerId = jobs.getEmployer().getId();
      employerName = jobs.getEmployer().getName();
    }

    return JobsDtoOut.builder()
            .title(jobs.getTitle())
            .quantity(jobs.getQuantity())
            .description(jobs.getDescription())
            .fields(fields)
            .provinces(provinces)
            .salary(jobs.getSalary())
            .expiredAt(jobs.getExpiredAt())
            .employerId(employerId)
            .employerName(employerName)
            .build();
  }

  /** Retrieves a summarized `JobsDtoOut` instance for pagination from a `Jobs` entity. */
  public static JobsDtoOut getPage(Jobs jobs) {
    Long employerId = null;
    String employerName = null;
    if (jobs.getEmployer() != null) {
      employerId = jobs.getEmployer().getId();
      employerName = jobs.getEmployer().getName();
    }

    return JobsDtoOut.builder()
            .id(jobs.getId())
            .title(jobs.getTitle())
            .quantity(jobs.getQuantity())
            .salary(jobs.getSalary())
            .expiredAt(jobs.getExpiredAt())
            .employerId(employerId)
            .employerName(employerName)
            .build();
  }
}

