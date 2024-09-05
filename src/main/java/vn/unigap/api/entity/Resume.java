package vn.unigap.api.entity;

import jakarta.persistence.CascadeType;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Represents a Resume entity in the recruitment application system. */
@Builder
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
