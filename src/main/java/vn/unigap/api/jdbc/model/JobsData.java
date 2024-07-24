package vn.unigap.api.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsData {
    private Long id;
    private Long employerId;
    private String title;
    private Integer quantity;
    private String description;
    private Integer salary;
    private String fields;
    private String fieldsName;
    private String provinces;
    private String provincesName;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private Date expiredAt = new Date();
    private EmployerData employerData;
}
