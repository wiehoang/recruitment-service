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
@Table(name = "JOB")
public class Job {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMPLOYER_ID")
    private Long employerId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DESCRIPTION")
    private String description;

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

    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;
}
