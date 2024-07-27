package vn.unigap.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Seeker;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeekerDtoOut {

    private String name;

    private String birthday;

    private String address;

    private Integer provinceId;

    private String provinceName;

    public static SeekerDtoOut from(Seeker seeker) {
        Integer provinceId = null;
        String provinceName = null;
        if (seeker.getJobProvince() != null) {
            provinceId = seeker.getJobProvince().getId();
            provinceName = seeker.getJobProvince().getName();
        }

        return SeekerDtoOut.builder()
                .name(seeker.getName())
                .birthday(seeker.getBirthday())
                .address(seeker.getAddress())
                .provinceId(provinceId)
                .provinceName(provinceName)
                .build();
    }
}
