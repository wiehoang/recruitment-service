package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Employer;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoOut {
    private Long id;
    private String email;
    private String name;
    private Integer provinceId;
    private String provinceName;
    private String description;

    // Add factory method
    public static EmployerDtoOut from(Employer employer) {
        return new EmployerDtoOut(employer.getId(),
                                employer.getEmail(),
                                employer.getName(),
                                employer.getProvince().getId(),
                                employer.getProvince().getName(),
                                employer.getDescription());
    }
}
