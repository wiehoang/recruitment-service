package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerDtoIn {

    @NotNull
    private String name;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String address;

    @NotNull
    private Integer province;

}
