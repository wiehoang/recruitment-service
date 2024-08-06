package vn.unigap.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOB_PROVINCE")
public class JobProvince {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "jobProvince", cascade = CascadeType.PERSIST)
    private Set<Employer> employers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "jobProvince")
    private Set<JobsToJobProvince> jobsToJobProvinces = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "jobProvince", cascade = CascadeType.PERSIST)
    private Set<Seeker> seekers = new HashSet<>();
}
