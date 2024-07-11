package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYER")
public class Employer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVINCE")
    private JobProvince province;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
