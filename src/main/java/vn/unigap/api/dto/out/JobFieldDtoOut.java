package vn.unigap.api.dto.out;

import lombok.Builder;
import lombok.Data;
import vn.unigap.api.entity.JobField;


@Builder
@Data
public class JobFieldDtoOut {
    private Long id;
    private String name;

    public static JobFieldDtoOut from(JobField jobField) {
        return JobFieldDtoOut.builder()
                .id(jobField.getId())
                .name(jobField.getName())
                .build();
    }
}
