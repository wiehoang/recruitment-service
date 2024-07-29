package vn.unigap.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeekerDtoOut {

    private Long id;
    private String name;
    private String birthday;
    private String address;
    private Integer provinceId;
    private String provinceName;

}
