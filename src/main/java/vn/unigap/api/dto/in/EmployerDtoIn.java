package vn.unigap.api.dto.in;

import jakarta.validation.constraints.*;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoIn {
    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private int provinceId;

    private String description;
}
