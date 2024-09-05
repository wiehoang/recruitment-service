package vn.unigap.api.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.entity.JobsToJobProvince;


/** Manages `JobsToJobProvince` entity with CRUD operations and custom queries. */
public interface JobsToJobProvinceRepository extends JpaRepository<JobsToJobProvince, Long> {

  @Query("SELECT jtp.jobProvince FROM JobsToJobProvince jtp WHERE jtp.jobs.id = :jobId")
  Set<JobProvince> findJobProvinceByJobId(@Param("jobId") Long jobId);

}
