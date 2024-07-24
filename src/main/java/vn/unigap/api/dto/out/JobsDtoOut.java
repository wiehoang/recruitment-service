package vn.unigap.api.dto.out;

import lombok.*;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Jobs;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;


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

    public static JobsDtoOut fromCreate(Jobs jobs, Set<Long> fieldIds, Set<Long> provinceIds) {
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

    public static JobsDtoOut fromUpdate(Jobs jobs, Set<Long> fieldIds, Set<Long> provinceIds) {
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

    public static JobsDtoOut fromGet(Jobs jobs, Set<JobFieldDtoOut> fields, Set<JobProvinceDtoOut> provinces) {
        return JobsDtoOut.builder()
                .title(jobs.getTitle())
                .quantity(jobs.getQuantity())
                .description(jobs.getDescription())
                .fields(fields)
                .provinces(provinces)
                .salary(jobs.getSalary())
                .expiredAt(jobs.getExpiredAt())
                .employerId(jobs.getEmployer() != null ? jobs.getEmployer().getId() : -1)
                .employerName(jobs.getEmployer() != null ? jobs.getEmployer().getName() : "")
                .build();
    }
}
