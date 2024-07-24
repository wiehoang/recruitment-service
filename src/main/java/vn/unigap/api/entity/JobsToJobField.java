package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOBS_TO_JOB_FIELD")
public class JobsToJobField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_ID")
    private Jobs jobs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_FIELD_ID")
    private JobField jobField;

}
