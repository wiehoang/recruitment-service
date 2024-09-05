package vn.unigap.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Resume;


/**
 * The `ResumeDtoOut` class represents the Data Transfer Object (DTO)
 * for responding a Resume object.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDtoOut {

  private Long id;
  private Long seekerId;
  private String seekerName;
  private String careerObj;
  private String title;
  private Integer salary;
  private Set<Long> fieldIds;
  private Set<Long> provinceIds;
  private Set<JobFieldDtoOut> fields;
  private Set<JobProvinceDtoOut> provinces;

  /** Creates a `ResumeDtoOut` instance from a `Resume` entity
   * with field and province IDs that stored in a set.
   */
  public static ResumeDtoOut save(Resume resume, Set<Long> fieldIds, Set<Long> provinceIds) {
    return ResumeDtoOut.builder()
            .seekerId(resume.getSeeker().getId())
            .careerObj(resume.getCareerObj())
            .title(resume.getTitle())
            .salary(resume.getSalary())
            .fieldIds(fieldIds)
            .provinceIds(provinceIds)
            .build();
  }

  /** Retrieves a `ResumeDtoOut` instance from a `Resume` entity
   * with sets of JobFieldDtoOut and JobProvinceDtoOut objects.
   */
  public static ResumeDtoOut get(Resume resume, Set<JobFieldDtoOut> fields,
                                 Set<JobProvinceDtoOut> provinces) {

    // Handle null Seeker's object
    Long seekerId = null;
    String seekerName = null;
    if (resume.getSeeker() != null) {
      seekerId = resume.getSeeker().getId();
      seekerName = resume.getSeeker().getName();
    }

    return ResumeDtoOut.builder()
            .id(resume.getId())
            .seekerId(seekerId)
            .seekerName(seekerName)
            .careerObj(resume.getCareerObj())
            .title(resume.getTitle())
            .salary(resume.getSalary())
            .fields(fields)
            .provinces(provinces)
            .build();

  }

  /** Retrieves a summarized `ResumeDtoOut` instance for pagination from a `Resume` entity. */
  public static ResumeDtoOut getPage(Resume resume) {

    // Handle null Seeker's object
    Long seekerId = null;
    String seekerName = null;
    if (resume.getSeeker() != null) {
      seekerId = resume.getSeeker().getId();
      seekerName = resume.getSeeker().getName();
    }

    return ResumeDtoOut.builder()
            .id(resume.getId())
            .seekerId(seekerId)
            .seekerName(seekerName)
            .careerObj(resume.getCareerObj())
            .title(resume.getTitle())
            .salary(resume.getSalary())
            .build();
  }

}

