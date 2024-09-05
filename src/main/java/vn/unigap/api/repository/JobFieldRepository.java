package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.JobField;


/** Manages `JobField` entity with CRUD operations and custom queries. */
@Repository
public interface JobFieldRepository extends JpaRepository<JobField, Long> {
}
