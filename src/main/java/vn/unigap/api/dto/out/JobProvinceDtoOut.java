package vn.unigap.api.dto.out;

import lombok.Builder;
import lombok.Data;
import vn.unigap.api.entity.JobProvince;


@Builder
@Data
public class JobProvinceDtoOut {
    private Integer id;
    private String name;

    public static JobProvinceDtoOut from(JobProvince jobProvince) {
        return JobProvinceDtoOut.builder()
                .id(jobProvince.getId())
                .name(jobProvince.getName())
                .build();
    }
}
