package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESUME")
public class Resume {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SEEKER_ID")
    private Long seekerId;

    @Column(name = "CAREER_OBJ")
    private String careerObj;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SALARY")
    private Integer salary;

    @Column(name = "FIELDS")
    private String fields;

    @Column(name = "PROVINCES")
    private String provinces;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
