package vn.unigap.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Represents an Employer entity in the recruitment application system. */
@Builder
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
  private JobProvince jobProvince;

  @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
  private String description;

  @Column(name = "CREATED_AT")
  private Date createdAt = new Date();

  @Column(name = "UPDATED_AT")
  private Date updatedAt = new Date();

  @OneToMany(mappedBy = "employer")
  private Set<Jobs> jobs = new HashSet<>();

}
