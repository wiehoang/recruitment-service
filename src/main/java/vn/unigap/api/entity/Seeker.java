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


/** Represents a Seeker entity in the recruitment application system. */
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
