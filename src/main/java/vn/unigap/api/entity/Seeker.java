package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEEKER")
public class Seeker {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVINCE", nullable = true)
    private JobProvince jobProvince;

    @Column(name = "CREATED_AT")
    private Date createdAt = new Date();

    @Column(name = "UPDATED_AT")
    private Date updatedAt = new Date();

    @OneToMany(mappedBy = "seeker")
    private Set<Resume> resumes = new HashSet<>();
}
