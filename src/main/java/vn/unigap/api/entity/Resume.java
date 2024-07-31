package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SEEKER_ID")
    private Seeker seeker;

    @Column(name = "CAREER_OBJ", columnDefinition = "TEXT")
    private String careerObj;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SALARY")
    private Integer salary;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.PERSIST)
    private List<ResumeToJobField> resumeToJobField = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.PERSIST)
    private List<ResumeToJobProvince> resumeToJobProvince = new ArrayList<>();

    @Column(name = "CREATED_AT")
    private Date createdAt = new Date();

    @Column(name = "UPDATED_AT")
    private Date updatedAt = new Date();
}
