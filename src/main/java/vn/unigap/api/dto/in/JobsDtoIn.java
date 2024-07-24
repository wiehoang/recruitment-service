package vn.unigap.api.dto.in;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDtoIn {

    @NotNull
    private String title;

    @NotNull
    private Long employerId;

    @NotNull
    private Integer quantity;

    @NotNull
    private String description;

    @NotNull
    private String fieldIds;

    @NotNull
    private String provinceIds;

    @NotNull
    private Integer salary;

    @NotNull
    private Date expiredAt = new Date();

}
