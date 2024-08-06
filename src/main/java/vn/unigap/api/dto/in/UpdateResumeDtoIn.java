package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UpdateResumeDtoIn {

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
