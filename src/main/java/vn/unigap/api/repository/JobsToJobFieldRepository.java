package vn.unigap.api.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.JobsToJobField;


/** Manages `JobsToJobField` entity with CRUD operations and custom queries. */
public interface JobsToJobFieldRepository extends JpaRepository<JobsToJobField, Long> {

  @Query("SELECT jtf.jobField FROM JobsToJobField jtf WHERE jtf.jobs.id = :jobId")
  Set<JobField> findJobFieldByJobId(@Param("jobId") Long jobId);

}
