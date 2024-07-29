package vn.unigap.api.dto.out;

import lombok.*;


@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoOut {

    private Long id;
    private String email;
    private String name;
    private Integer provinceId;
    private String provinceName;
    private String description;

}
