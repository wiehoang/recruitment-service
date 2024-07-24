package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOBS")
public class Jobs {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYER_ID")
    private Employer employer;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "SALARY")
    private Integer salary;

    @OneToMany(mappedBy = "jobs", cascade = CascadeType.PERSIST)
    private List<JobsToJobField> jobsToJobField = new ArrayList<>();

    @OneToMany(mappedBy = "jobs", cascade = CascadeType.PERSIST)
    private List<JobsToJobProvince> jobsToJobProvince = new ArrayList<>();

    @Column(name = "CREATED_AT")
    private Date createdAt = new Date();

    @Column(name = "UPDATED_AT")
    private Date updatedAt = new Date();

    @Column(name = "EXPIRED_AT")
    private Date expiredAt = new Date();

}
