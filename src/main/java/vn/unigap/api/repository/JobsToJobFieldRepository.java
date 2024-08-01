package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.JobsToJobField;
import java.util.Set;


public interface JobsToJobFieldRepository extends JpaRepository<JobsToJobField, Long> {
    @Query("SELECT jtf.jobField FROM JobsToJobField jtf WHERE jtf.jobs.id = :jobId")
    Set<JobField> findJobFieldByJobId(@Param("jobId") Long jobId);
}
