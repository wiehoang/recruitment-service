package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobsToJobProvince;
import java.util.Set;


public interface JobsToJobProvinceRepository extends JpaRepository<JobsToJobProvince, Long> {
    @Query("SELECT jtp.jobProvince.id FROM JobsToJobProvince jtp WHERE jtp.jobs.id = :jobId")
    Set<Long> findProvinceIdByJobId(@Param("jobId") Long jobId);
}
