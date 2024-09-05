package vn.unigap.api.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.entity.ResumeToJobProvince;


/** Manages `ResumeToJobProvince` entity with CRUD operations and custom queries. */
public interface ResumeToJobProvinceRepository extends JpaRepository<ResumeToJobProvince, Long> {

  @Query("SELECT rtp.jobProvince FROM ResumeToJobProvince rtp WHERE rtp.resume.id = :resumeId")
  Set<JobProvince>  findJobProvinceByResumeId(@Param("resumeId") Long resumeId);

}
