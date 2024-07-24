package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.JobProvince;

import java.util.List;
import java.util.Set;


@Repository
public interface JobProvinceRepository extends JpaRepository<JobProvince, Long> {
}
