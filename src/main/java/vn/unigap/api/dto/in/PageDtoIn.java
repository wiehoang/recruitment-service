package vn.unigap.api.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
public class PageDtoIn {
    @NotNull
    @Min(1)
    private Integer page;

    @NotNull
    @Min(1)
    @Max(500)
    private Integer pageSize;
}
