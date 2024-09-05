package vn.unigap.api.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.ResumeToJobField;


/** Manages `ResumeToJobField` entity with CRUD operations and custom queries. */
public interface ResumeToJobFieldRepository extends JpaRepository<ResumeToJobField, Long> {

  @Query("SELECT rtf.jobField FROM ResumeToJobField rtf WHERE rtf.resume.id = :resumeId")
  Set<JobField> findJobFieldByResumeId(@Param("resumeId") Long resumeId);

}
