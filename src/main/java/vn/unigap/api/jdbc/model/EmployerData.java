package vn.unigap.api.jdbc.model;

import lombok.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerData {
    private Long id;
    private String email;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
