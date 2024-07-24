package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Jobs;


@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {
    Page<Jobs> findAllByEmployerId(Long employerId, Pageable pageable);
}
